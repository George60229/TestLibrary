package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/viewByAuthorServlet")
    public class ViewByAuthorServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String sid = request.getParameter("author");


            List<Book> myBooks = BookRepository.getBooksByAuthor(sid);

            out.print(myBooks);
            out.close();
        }
    }
