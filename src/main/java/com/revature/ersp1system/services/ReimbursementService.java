package com.revature.ersp1system.services;

import com.revature.ersp1system.models.Reimbursement;
import com.revature.ersp1system.models.Status;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.ReimbursementDAO;

import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    private ReimbursementDAO reimbursementDao = new ReimbursementDAO();

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        return Collections.emptyList();
    }

    public Reimbursement create(Reimbursement reimbursement, String userName){
        return reimbursementDao.create(reimbursement, userName);
    }

    public List<Reimbursement> getReimbursementByAuthor(int id){
        return reimbursementDao.getReimbursementByAuthor(id);
    }

    public List<Reimbursement> getAllReimbursement() {

        return reimbursementDao.getAllReimbursements();
    }

    public Reimbursement update(Reimbursement unprocessedReimbursement){
        return reimbursementDao.update(unprocessedReimbursement);
    }

}
