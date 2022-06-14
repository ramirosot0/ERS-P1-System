package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Optional;

import static java.sql.Types.BIGINT;

@WebServlet(name = "register", value = "/register")
public class RegistrationServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("name");
        String userLastName = request.getParameter("lastname");
        String userEmail = request.getParameter("email");
        String userUserName = request.getParameter("username");
        String userPassword = request.getParameter("pass");
        RequestDispatcher  dispatcher = null;

        //security check
        if (userName == null || userName.equals("")){
            request.setAttribute("status", "invalidName");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        else if (userLastName == null || userLastName.equals("")){
            request.setAttribute("status", "invalidLastName");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        else if (userEmail == null || userEmail.equals("")){
            request.setAttribute("status", "invalidEmail");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        else if (userUserName == null || userUserName.equals("")){
            request.setAttribute("status", "invalidUserName");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        else if (userPassword == null || userPassword.equals("")){
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        else {
            UserDAO userDao = new UserDAO();
            User user1 = new User(userName, userLastName, userEmail, userUserName, userPassword);
            User user = userDao.create(user1);

            dispatcher = request.getRequestDispatcher("registration.jsp");
            request.setAttribute("status", "success");

            System.out.println(user.getId() + "  " + user.getName());
        }

        dispatcher.forward(request, response);
    }
}
