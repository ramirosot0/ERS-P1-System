package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher  dispatcher = null;

        String thisusername= "";
        if (session != null) {
            thisusername = (String) session.getAttribute("username");
        }


        UserDAO userDao = new UserDAO();
        Optional<User> user = userDao.getByUsername(thisusername);

        if (user.isPresent()){
            session.setAttribute("name", user.get().getName().toUpperCase());
            session.setAttribute("lastname", user.get().getLastName().toUpperCase());
            session.setAttribute("email", user.get().getEmail());
            session.setAttribute("role", user.get().getRole());
            session.setAttribute("username", user.get().getUsername());

            dispatcher = request.getRequestDispatcher("Profile.jsp");
        }
        System.out.println(user.get().getName());
        System.out.println(user.get().getUsername());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
