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


@WebServlet("/viewByNameServlet")
    public class ViewByNameServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String sid = request.getParameter("name");


            List<Book> myBook = BookRepository.getBooksByName(sid);

            out.print(myBook);
            out.close();
        }
    }

