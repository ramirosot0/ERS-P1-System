package com.revature.ersp1system.repositories;

import com.revature.ersp1system.models.Role;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.util.ConnectionFactory;

import java.sql.*;
import java.util.Optional;

import static java.sql.Types.BIGINT;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        //TODO: empty user object?
        Optional<User> optionalUser = Optional.empty();
        Connection conn = null;
        try {
            //Create Query to Execute (Statement, Prepared Statement, CallableStatement)
            String query = "select * from user_table where username='" + username + "'";
            //instantiate connection factory
            conn = ConnectionFactory.getInstance().getConnection();
            //statement interface
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                //check user role
                if (rs.getString("role").equalsIgnoreCase("finance manager")){
                    user.setRole(Role.FINANCE_MANAGER);
                } else if (rs.getString("role").equalsIgnoreCase("employee")) {
                    user.setRole(Role.EMPLOYEE);
                }
                optionalUser = Optional.of(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return optionalUser;
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        Connection conn = null;
        try{
            //Create Query to Execute (Statement, Prepared Statement, CallableStatement)
            String query = "insert into user_table(firstname, lastname, email, role, phone, address, username, password) values(?,?,?,?,?,?,?,?)";
            //instantiate connection factory
            conn = ConnectionFactory.getInstance().getConnection();
            //statement interface
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userToBeRegistered.getName());
            pst.setString(2, userToBeRegistered.getLastName());
            pst.setString(3, userToBeRegistered.getEmail());
            pst.setString(4, "Employee");
            pst.setString(5, null);
            pst.setString(6, null);
            pst.setString(7, userToBeRegistered.getUsername());
            pst.setString(8, userToBeRegistered.getPassword());

            int status = pst.executeUpdate();
            if(status > 0) {
//                ResultSet pk = pst.getGeneratedKeys();
//                while(pk.next()) {
//                    userToBeRegistered.setId(pk.getInt("id"));
//                }
//                conn.commit();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userToBeRegistered;
    }
}
