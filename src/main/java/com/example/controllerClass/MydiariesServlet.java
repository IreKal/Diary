package com.example.controllerClass;

import com.example.modelClass.User;
import com.example.otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MydiariesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loginUser = Db.getLoginUser(request);
        request.setAttribute("viewFile", "mydiaries.jsp");
        request.setAttribute("pageTitle", "My diaries");
        request.setAttribute("diaryList", Db.diaryRepository()
                .getAllByUserId(loginUser.getId(), true));
        Db.view(request, response);
    }
}
