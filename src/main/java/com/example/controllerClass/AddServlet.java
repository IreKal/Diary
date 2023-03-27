package com.example.controllerClass;

import com.example.modelClass.Diary;
import com.example.modelClass.User;
import com.example.otherClass.Db;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewFile", "add.jsp");
        DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        request.setAttribute("pageTitle", "Add new diary / "
                + sdf.format(new Date()));
        Db.view(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = Db.getLoginUser(request);
        String diaryContent = request.getParameter("diaryContent");
        boolean visible = request.getParameter("visible") != null;

        Diary newDiary = new Diary();
        newDiary.setUserId(loginUser.getId());
        newDiary.setDateOfDiary(new Date());
        newDiary.setContent(diaryContent);
        newDiary.setVisibility(visible);

        boolean addResult = Db.diaryRepository().add(newDiary);
        if (!addResult) {
            request.setAttribute("viewFile", "add.jsp");
            DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            request.setAttribute("pageTitle", "Add new diary / "
                    + sdf.format(new Date()));
            request.setAttribute("message", "Something went wrong");
            Db.view(request, response);
        } else {
            response.sendRedirect("mydiaries");
        }

    }
}
