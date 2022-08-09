package com.example.demo.servletsBook;

import com.example.demo.Book;
import com.example.demo.BookRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/viewServletOrderByYear")

public class ViewServletOrderByYear extends HttpServlet{



        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            List<Book> list = BookRepository.getAllBooksOrderByYear();
            for (Book myBook : list) {
                out.print(myBook+System.lineSeparator());
            }
            out.close();
        }
    }
