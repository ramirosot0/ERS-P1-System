package com.revature.ersp1system.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        super();
    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static ConnectionFactory getInstance() {
        if(instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    /**
     * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            // 1) Load & Register the Driver (Depends on the DBMS used)
            Class.forName("com.mysql.cj.jdbc.Driver");

            String username = "root";
            String password = "root1234";
            String url = "jdbc:mysql://localhost:3306/project1_schema?useSSL=false";

            //	2)	Establish the Connection between Java and RDBMS
            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
