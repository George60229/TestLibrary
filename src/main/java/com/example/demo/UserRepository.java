package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where isblocked= false");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                User myUser=createUser(rs);

                listUsers.add(myUser);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }
    public static int delete(int id) {

        int status = 0;

        try {
            Connection connection = UserRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }
    public static int update(User myUser) {

        int status = 0;

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update users set isblocked=true where id=? ");
            ps.setInt(1, myUser.getId());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }


    public static User createUser(ResultSet rs) throws SQLException {
        User myUser = new User();

        myUser.setId(rs.getInt(1));
        myUser.setRole(rs.getString(2));
        myUser.setPassword(rs.getString(3));
        myUser.setLogin(rs.getString(4));


        return myUser;
    }
}
