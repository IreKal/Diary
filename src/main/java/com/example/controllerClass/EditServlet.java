package com.example.controllerClass;

import com.example.modelClass.Diary;
import com.example.otherClass.Db;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diaryId = Integer.parseInt(request.getParameter("diary_id"));
        Diary foundDiary = Db.diaryRepository().get(diaryId);

        if (foundDiary != null) {
            request.setAttribute("viewFile", "edit.jsp");
            DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            request.setAttribute("pageTitle", "Edit diary / "
                    + sdf.format(foundDiary.getDateOfDiary()));
            request.setAttribute("diary", foundDiary);
            Db.view(request, response);
        } else {
            response.sendRedirect("mydiaries");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diaryId = Integer.parseInt(request.getParameter("diary_id"));
        Diary foundDiary = Db.diaryRepository().get(diaryId);

        if (foundDiary != null) {
            String diaryContent = request.getParameter("diaryContent");
            boolean visible = request.getParameter("visible") != null;
            foundDiary.setContent(diaryContent);
            foundDiary.setVisibility(visible);

            Diary editResult = Db.diaryRepository()
                    .update(foundDiary.getId(), foundDiary);

            if (editResult != null) {
                response.sendRedirect("mydiaries");
            } else {
                request.setAttribute("viewFile", "edit.jsp");
                DateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                request.setAttribute("pageTitle", "Edit diary / "
                        + sdf.format(foundDiary.getDateOfDiary()));
                request.setAttribute("message", "Something went wrong");
                Db.view(request, response);
            }
        } else {
            response.sendRedirect("mydiaries");
        }

    }
}
