package com.revature.ersp1system.models;

import java.io.InputStream;
import java.util.Date;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

    private String description;
    private Date creation_date;
    private Date resolution_date;
    private InputStream receipt;

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, int author, int resolver, double amount) {
        super(id, status, author, resolver, amount);
    }

    public Reimbursement(int id, Status status, int author, int resolver, double amount, String description, Date creation_date, Date resolution_date, InputStream receipt) {
        super(id, status, author, resolver, amount);
        this.description = description;
        this.creation_date = creation_date;
        this.resolution_date = resolution_date;
        this.receipt = receipt;
    }

    public Reimbursement(String description, Date creation_date, Date resolution_date, InputStream receipt) {
        this.description = description;
        this.creation_date = creation_date;
        this.resolution_date = resolution_date;
        this.receipt = receipt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getResolution_date() {
        return resolution_date;
    }

    public void setResolution_date(Date resolution_date) {
        this.resolution_date = resolution_date;
    }

    public InputStream getReceipt() {
        return receipt;
    }

    public void setReceipt(InputStream receipt) {
        this.receipt = receipt;
    }
}
