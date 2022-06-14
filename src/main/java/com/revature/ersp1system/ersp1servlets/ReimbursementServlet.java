package com.revature.ersp1system.ersp1servlets;

import com.revature.ersp1system.models.Reimbursement;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.services.AuthService;
import com.revature.ersp1system.services.ReimbursementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "reimbursement", value = "/reimbursement")
@MultipartConfig(maxFileSize = 16177215)
public class ReimbursementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ReimbursementService reimbursementService = new ReimbursementService();
    AuthService authService = new AuthService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = null;
        RequestDispatcher  dispatcher = null;

        Reimbursement reimbursement = new Reimbursement();
        double amount = Double.parseDouble(request.getParameter("amount"));
        String description = request.getParameter("description");

        String thisusername= "";
        if (session != null) {
            thisusername = (String) session.getAttribute("username");
        }

        // Input stream of the upload file
        InputStream inputStream = null;
        String message = null;

        // Obtains the upload file
        // part in this multipart request
        Part filePart = request.getPart("receipt");

        if (filePart != null) {
            // Prints out some information
            // for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        if (authService.retrieveCurrentUser(thisusername).isPresent()){
            user = authService.retrieveCurrentUser(thisusername).get();
        }

        String username = user.getUsername();
        System.out.println("username in reimburse servlet =" + username);

        reimbursement.setAmount(amount);
        reimbursement.setDescription(description);
        reimbursement.setReceipt(inputStream);
        reimbursement.setAuthor(user.getId());

        // Sends the statement to the
        // database server
        Reimbursement reimburse = reimbursementService.create(reimbursement, username);

        if (reimburse != null){
            message = "file uploaded";
            dispatcher = request.getRequestDispatcher("index.jsp");
            System.out.println(message);
            request.setAttribute("status", "success");
        } else {
            System.out.println("Error creating reimbursement request!");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
