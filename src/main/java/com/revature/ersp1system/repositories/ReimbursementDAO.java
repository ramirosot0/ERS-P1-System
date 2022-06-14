package com.revature.ersp1system.repositories;

import com.revature.ersp1system.models.Reimbursement;
import com.revature.ersp1system.models.Status;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.services.AuthService;
import com.revature.ersp1system.util.ConnectionFactory;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {
    AuthService authService = new AuthService();

    public Reimbursement create(Reimbursement reimbursement, String userName){
        User user = null;
        if (authService.retrieveCurrentUser(userName).isPresent()){
            user = authService.retrieveCurrentUser(userName).get();
        }
        System.out.println("User id in Reimburse Servlet id=" + user.getId());

        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String insertQuery = "insert into reimbursement_table(status, author, resolver, amount, description, creation_date, resolution_date, receipt_image) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, String.valueOf(Status.PENDING));
            pstmt.setInt(2, user.getId());
            pstmt.setNull(3, Types.INTEGER);
            pstmt.setDouble(4, reimbursement.getAmount());
            pstmt.setString(5, reimbursement.getDescription());
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            pstmt.setTimestamp(6, date);
            pstmt.setNull(7, Types.TIMESTAMP);
            pstmt.setBlob(8, reimbursement.getReceipt());
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()){
                System.out.println(rs.getString(1));

//                Reimbursement reimburse = new Reimbursement();
//                reimburse.setId(rs.getInt("id"));
//                reimburse.setAmount(rs.getDouble("amount"));
//                reimburse.setAuthor(rs.getInt("author"));
//                reimburse.setResolver(rs.getInt("resolver"));
//                if (rs.getString("status").equalsIgnoreCase("pending")) {
//                    reimburse.setStatus(Status.PENDING);
//                } else if (rs.getString("status").equalsIgnoreCase("denied")) {
//                    reimburse.setStatus(Status.DENIED);
//                } else if (rs.getString("status").equalsIgnoreCase("approved")) {
//                    reimburse.setStatus(Status.APPROVED);
//                }
//                reimburse.setCreation_date(rs.getDate("creation_date"));
//                reimburse.setResolution_date(rs.getDate("resolution_date"));
//                reimburse.setReceipt((InputStream) rs.getBlob("receipt_image"));
//
//                System.out.println(reimburse.toString());

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return reimbursement;
    }

    public List<Reimbursement> getReimbursementByAuthor(int id){
        List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from reimbursement_table where author=" + id);
            while (rs.next()){
                Reimbursement reimburse = new Reimbursement();
                reimburse.setId(rs.getInt("id"));
                if (rs.getString("status").equalsIgnoreCase("pending")){
                    reimburse.setStatus(Status.PENDING);
                } else if (rs.getString("status").equalsIgnoreCase("denied")) {
                    reimburse.setStatus(Status.DENIED);
                } else if (rs.getString("status").equalsIgnoreCase("approved")) {
                    reimburse.setStatus(Status.APPROVED);
                }
                reimburse.setAuthor(rs.getInt("author"));
                reimburse.setResolver(rs.getInt("resolver"));
                reimburse.setAmount(rs.getDouble("amount"));
                reimburse.setDescription(rs.getString("description"));
                reimburse.setCreation_date(rs.getTimestamp("creation_date"));
                reimburse.setResolution_date(rs.getTimestamp("resolution_date"));
                //reimburse.setReceipt((InputStream) rs.getObject("receipt_image"));
                reimburse.setReceipt(rs.getBinaryStream("receipt_image"));

                reimbursementList.add(reimburse);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reimbursementList;
    }

    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();

        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from reimbursement_table");
            while (rs.next()){
                Reimbursement reimburse = new Reimbursement();
                reimburse.setId(rs.getInt("id"));
                if (rs.getString("status").equalsIgnoreCase("pending")){
                    reimburse.setStatus(Status.PENDING);
                } else if (rs.getString("status").equalsIgnoreCase("denied")) {
                    reimburse.setStatus(Status.DENIED);
                } else if (rs.getString("status").equalsIgnoreCase("approved")) {
                    reimburse.setStatus(Status.APPROVED);
                }
                reimburse.setAuthor(rs.getInt("author"));
                reimburse.setResolver(rs.getInt("resolver"));
                reimburse.setAmount(rs.getDouble("amount"));
                reimburse.setDescription(rs.getString("description"));
                reimburse.setCreation_date(rs.getTimestamp("creation_date"));
                reimburse.setResolution_date(rs.getTimestamp("resolution_date"));
                reimburse.setReceipt(rs.getBinaryStream("receipt_image"));

                reimbursementList.add(reimburse);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return reimbursementList;
    }

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
        Reimbursement reimburse = new Reimbursement();
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String updateQuery = "UPDATE reimbursement_table SET resolver = ?, status = ?,resolution_date = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, unprocessedReimbursement.getResolver());
            if (unprocessedReimbursement.getStatus().equals(Status.APPROVED))
                pstmt.setString(2, "Approved");
            if (unprocessedReimbursement.getStatus().equals(Status.DENIED))
                pstmt.setString(2, "Denied");
            pstmt.setInt(3, unprocessedReimbursement.getId());
            pstmt.execute();

            ResultSet rs = conn.createStatement()
                    .executeQuery("select * from reimbursement_table where id=" + unprocessedReimbursement.getId());
            if (rs.next()) {
                reimburse.setId(rs.getInt("id"));
                reimburse.setAmount(rs.getDouble("amount"));
                reimburse.setAuthor(rs.getInt("author"));
                reimburse.setResolver(rs.getInt("resolver"));

                reimburse.setDescription(rs.getString("description"));
                reimburse.setCreation_date(rs.getDate("creation_date"));
                reimburse.setResolution_date(rs.getDate("resolution_date"));
                if (rs.getString("status").equalsIgnoreCase("pending"))
                    reimburse.setStatus(Status.PENDING);
                if (rs.getString("status").equalsIgnoreCase("Approved"))
                    reimburse.setStatus(Status.APPROVED);
                if (rs.getString("status").equalsIgnoreCase("Denied"))
                    reimburse.setStatus(Status.DENIED);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return reimburse;
    }


}
