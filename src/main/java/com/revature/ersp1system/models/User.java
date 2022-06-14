package com.revature.ersp1system.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

    public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }

    public User(String name, String lastName, String email, Role role, String phone, String address, String username, String password) {
        super(name, lastName, email, role, phone, address, username, password);
    }

    public User(String name, String lastName, String email, String username, String password){
        super(name, lastName, email, username, password);
    }
}
