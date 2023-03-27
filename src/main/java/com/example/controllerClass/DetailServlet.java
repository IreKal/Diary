package com.example.controllerClass;

import com.example.modelClass.Diary;
import com.example.otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diaryId = Integer.parseInt(request.getParameter("diary_id"));
        Diary foundDiary = Db.diaryRepository().get(diaryId);

        if (foundDiary != null) {
            request.setAttribute("viewFile", "detail.jsp");
            request.setAttribute("pageTitle", foundDiary.getDateOfDiary());
            request.setAttribute("diary", foundDiary);
        }

        Db.view(request, response);
    }
}
