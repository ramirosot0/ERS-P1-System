package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.Reimbursement;
import com.revature.ersp1system.models.Status;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.services.AuthService;
import com.revature.ersp1system.services.ReimbursementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deny", value = "/deny")
public class DenyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ReimbursementService reimbursementService = new ReimbursementService();
    AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = null;
        String message = null;
        RequestDispatcher  dispatcher = null;

        String thisusername= "";
        if (session != null) {
            thisusername = (String) session.getAttribute("username");
        }
        if (authService.retrieveCurrentUser(thisusername).isPresent()){
            user = authService.retrieveCurrentUser(thisusername).get();
        }

        String username = user.getUsername();
        System.out.println("username in reimburse servlet =" + username);

        int id = Integer.parseInt(request.getParameter("id"));
        int author = Integer.parseInt(request.getParameter("author"));
        System.out.println("author of form: " + author);
        System.out.println("form id :" + id);

        Reimbursement reimbursement = new Reimbursement();

        reimbursement.setResolver(user.getId());
        reimbursement.setStatus(Status.DENIED);
        reimbursement.setId(id);

        System.out.println(reimbursement.getStatus());

        //check if user and author are the same
        Reimbursement reimburseApproved = null;
        if (author != user.getId()){
            reimburseApproved = reimbursementService.update(reimbursement);
        }
        // Sends the statement to the
        // database server


        if (reimburseApproved != null){
            message = "file updated";
            dispatcher = request.getRequestDispatcher("record");
            System.out.println(message);
        } else {
            System.out.println("Error creating reimbursement update!");
            dispatcher = request.getRequestDispatcher("Error.jsp");
        }
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
