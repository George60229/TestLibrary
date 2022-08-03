package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class BookRepository {


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

    public static int save(Book myBook) {
        int status = 0;
        try {
            Connection connection = BookRepository.getConnection();

            PreparedStatement ps = connection.prepareStatement("insert into books(name,author,country,amount,year) values (?,?,?,?,?)");
            ps.setString(1, myBook.getName());
            ps.setString(2, myBook.getAuthor());
            ps.setString(3, myBook.getCountry());
            ps.setInt(4,myBook.getAmount());

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
            PreparedStatement ps = connection.prepareStatement("update books set name=?,author=?,country=? where id=?");
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
            PreparedStatement ps = connection.prepareStatement("delete from books where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Book getBookById(int id) {

        Book myBook=null;

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            myBook = createBook(rs);


            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return myBook;
    }

    public static List<Book> getAllBooks() {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook=createBook(rs);

                listBooks.add(myBook);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }
    public static List<Book> getAllBooksOrderByYear() {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books order by year");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook=createBook(rs);

                listBooks.add(myBook);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }
    public static List<Book> getAllBooksOrderByName() {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books order by name");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook=createBook(rs);

                listBooks.add(myBook);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }
    public static List<Book> getAllBooksOrderByCountry() {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books order by country");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook=createBook(rs);

                listBooks.add(myBook);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }
    public static List<Book> getAllBooksOrderByAuthor() {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books order by author");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook=createBook(rs);

                listBooks.add(myBook);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }



    public static List<Book> getBooksByAuthor(String author) {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook = new Book();

                myBook.setId(rs.getInt(1));
                myBook.setName(rs.getString(2));
                myBook.setCountry(rs.getString(3));
                myBook.setAuthor(rs.getString(4));
                myBook.setAmount(rs.getInt(5));
                myBook.setYear(rs.getInt(6));

                if (myBook.getAuthor().equals(author)) {
                    listBooks.add(myBook);
                }
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;


    }
    public static List<Book> getBooksByName(String name) {

        List<Book> listBooks = new ArrayList<>();

        try {
            Connection connection = BookRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                Book myBook = createBook(rs);

                if (myBook.getAuthor().equals(name)) {
                    listBooks.add(myBook);
                }
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;


    }
    public static Book createBook(ResultSet rs) throws SQLException {
        Book myBook = new Book();

        myBook.setId(rs.getInt(1));
        myBook.setName(rs.getString(2));
        myBook.setCountry(rs.getString(3));
        myBook.setAuthor(rs.getString(4));
        myBook.setAmount(rs.getInt(5));
        myBook.setYear(rs.getInt(6));

        return myBook;
    }

}
