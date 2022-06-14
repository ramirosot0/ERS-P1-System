package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.Reimbursement;
import com.revature.ersp1system.models.Role;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.UserDAO;
import com.revature.ersp1system.services.ReimbursementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(name = "record", value = "/record")
public class RecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;

    public void init() {
        message = "Hello World!";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        UserDAO userDAO = new UserDAO();
        Optional<User> optionalUser = userDAO.getByUsername(username);
        User user = optionalUser.get();

        System.out.println("User = " + user);

        ReimbursementService reimburseService = new ReimbursementService();
        Role role = user.getRole();
        System.out.println("What role?=   " + role);

        int id = user.getId();
        int flag = 0;

        List<Reimbursement> reimburseList = new ArrayList<>();
        if (role.equals(Role.EMPLOYEE)){
            reimburseList = reimburseService.getReimbursementByAuthor(id);
        } else if (role.equals(Role.FINANCE_MANAGER)) {
            flag = 1;
            reimburseList = reimburseService.getAllReimbursement();
        }
        out.println("<html> <head> <title> Reimbursement Details </title> </head> <body>");

        String firstName = null;
        String lastName = null;
        String fullName = null;
        if (optionalUser.isPresent()) {
            firstName = user.getName();
            lastName = user.getLastName();
            fullName = firstName + lastName;
        }
        out.println("<h1>Welcome, " + firstName.toUpperCase() + " </h1>");
        out.println("<h3><a href='Reimbursement.jsp'>Create a New Reimbursement Request </a></h3> <hr>");
        out.println(
                "<table border='1'> <thead> <tr> <th> Form Id </th> <th> Status </th> <th> Author </th> <th> Resolver </th> <th> Amount </th> <th> Description </th> <th>Creation Date </th> <th> Resolution Date </th> <th>Receipt Image </th> ");
        if (flag == 1) {
            out.println("<th>Actions </th>");
        }
        out.println("</tr></thead><tbody>");
        for (Reimbursement reimbursement : reimburseList) {
            out.print("<tr> <td>" + reimbursement.getId() + "</td>");
            out.print("<td>" + reimbursement.getStatus() + "</td>");
            out.print("<td>" + reimbursement.getAuthor() + "</td>");
            out.print("<td>" + reimbursement.getResolver() + "</td>");
            out.print("<td>" + reimbursement.getAmount() + "</td>");
            out.print("<td>" + reimbursement.getDescription() + "</td>");
            out.print("<td>" + reimbursement.getCreation_date() + "</td>");
            out.print("<td>" + reimbursement.getResolution_date() + "</td>");
            out.print("<td>" + reimbursement.getReceipt() + "</td>");
            if (flag == 1) {
                out.print("<td> <a href=\"approve?id=" +reimbursement.getId()+"&amp;author="+reimbursement.getAuthor()+"\"> Approve </a> </td>");
                out.println("<td> <a href=\"deny?id=" +reimbursement.getId()+"&amp;author="+reimbursement.getAuthor()+"\"> Deny </a> </td>");

            }
            out.println("</tr>");
        }
        out.print("</tbody></table>");
        out.println("<h4><a href=\"logout\">Logout</a></h4>");
        out.println("</body> </html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
