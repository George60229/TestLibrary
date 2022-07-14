package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {


        public static Connection getConnection() {

            Connection connection = null;
            String url = "jdbc:postgresql://localhost:5432/employee";
            String user = "postgres";
            String password = "174180183";

            try {
                connection = DriverManager.getConnection(url, user, password);
                if (connection != null) {
                    System.out.println("Connected to the PostgreSQL server successfully.");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } catch (SQLException sqlException) {
                System.out.println(sqlException);
            }
            return connection;
        }

        public static int save(Book myBook) {
            int status = 0;
            try {
                Connection connection = BookRepository.getConnection();
                //todo create db
                PreparedStatement ps = connection.prepareStatement("insert into users(name,email,country) values (?,?,?)");
                ps.setString(1, myBook.getName());
                ps.setString(2, myBook.getAuthor());
                ps.setString(3, myBook.getCountry());

                status = ps.executeUpdate();
                connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return status;
        }

        public static int update(Book myBook) {

            int status = 0;

            try {
                Connection connection = BookRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("update users set name=?,email=?,country=? where id=?");
                ps.setString(1, myBook.getName());
                ps.setString(2, myBook.getAuthor());
                ps.setString(3, myBook.getCountry());
                ps.setInt(4, myBook.getId());

                status = ps.executeUpdate();
                connection.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return status;
        }

        public static int delete(int id) {

            int status = 0;

            try {
                Connection connection = BookRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from users where id=?");
                ps.setInt(1, id);
                status = ps.executeUpdate();

                connection.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return status;
        }

        public static Book getBookById(int id) {

            Book myBook = new Book();

            try {
                Connection connection = BookRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from users where id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    myBook.setId(rs.getInt(1));
                    myBook.setName(rs.getString(2));
                    myBook.setAuthor(rs.getString(3));
                    myBook.setCountry(rs.getString(4));
                }
                connection.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return myBook;
        }

        public static List<Book> getAllBooks() {

            List<Book> listEmployees = new ArrayList<>();

            try {
                Connection connection = BookRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from users");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Book myBook = new Book();

                    myBook.setId(rs.getInt(1));
                    myBook.setName(rs.getString(2));
                    myBook.setAuthor(rs.getString(3));
                    myBook.setCountry(rs.getString(4));

                    listEmployees.add(myBook);
                }
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return listEmployees;
        }


}
