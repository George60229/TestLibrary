package com.example.demo.servletsUser;

import com.example.demo.Book;
import com.example.demo.BookRepository;
import com.example.demo.User;
import com.example.demo.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/viewUserServlet")
    public class ViewUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<User> list = UserRepository.getAllUsers();

        for (User myUser : list) {

                out.print(myUser + System.lineSeparator());

        }
        out.close();
    }
}

