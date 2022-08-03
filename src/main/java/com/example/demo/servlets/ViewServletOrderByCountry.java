package com.example.demo.servlets;

import com.example.demo.Book;
import com.example.demo.BookRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewServletOrderByCountry")

public class ViewServletOrderByCountry extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Book> list = BookRepository.getAllBooksOrderByCountry();
        out.println(list);
        for (Book myBook : list) {
            out.print(myBook);
        }
        out.close();
    }
}

