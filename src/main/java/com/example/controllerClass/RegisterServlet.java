package com.example.controllerClass;

import com.example.modelClass.User;
import com.example.otherClass.Db;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("viewFile", "register.jsp");
        request.setAttribute("pageTitle", "Register");
        Db.view(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] parameters = {"first_name", "last_name", "email", "password"};
        boolean checkResult = Db
                .checkParameters(parameters, request.getParameterMap());

        if (!checkResult) {
            request.setAttribute("viewFile", "register.jsp");
            request.setAttribute("message", "Please fill all field");
            Db.view(request, response);
        } else {

            String fistName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User newUser = new User();
            newUser.setFirstName(fistName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword(Db.md5(password));

            boolean registerResult = Db.userRepository().add(newUser);
            if (registerResult) {
                response.sendRedirect("login");
            } else {
                request.setAttribute("message", "Something went wrong.");
                request.setAttribute("viewFile", "register.jsp");
                request.setAttribute("pageTitle", "Register");
                Db.view(request, response);
            }
        }

    }
}
