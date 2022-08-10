package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.System.out;

public class UserRepository {


    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/library";
        String user = "postgres";
        String password = "174180183";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                out.println("Connected to the PostgreSQL server successfully.");
            } else {
                out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            out.println(sqlException);
        }
        return connection;
    }
    public static int save(User myUser) {
        int status = 0;
        try {





            Connection connection = UserRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into users(login,password,role) values (?,?,?)");



            ps.setString(1, myUser.getLogin());
            ps.setString(2, myUser.getPassword());
            ps.setString(3, myUser.getRole());


            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
}
