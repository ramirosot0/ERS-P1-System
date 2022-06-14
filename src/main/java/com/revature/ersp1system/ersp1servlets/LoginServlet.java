package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.AbstractUser;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userUserName = request.getParameter("username");
        String userPassword = request.getParameter("password");
        RequestDispatcher  dispatcher = null;
        Connection conn = null;
        //session object
        HttpSession session = request.getSession();

        if (userUserName == null || userUserName.equals("")){
            request.setAttribute("status", "invalidUsername");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        if (userPassword == null || userPassword.equals("")){
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

        UserDAO userDao = new UserDAO();
        Optional<User> user = userDao.getByUsername(userUserName);

        if (user.isPresent() && user.get().getPassword().equals(userPassword)){
            session.setAttribute("name", user.get().getName().toUpperCase());
            session.setAttribute("username", user.get().getUsername());
            // siva response.sendRedirect("HomeServlet?username=" + userUserName);
            dispatcher = request.getRequestDispatcher("index.jsp");
        }else {
            request.setAttribute("status", "failed");
            dispatcher = request.getRequestDispatcher("login.jsp");
        }
        //call dispatcher
        dispatcher.forward(request, response);
    }
}
