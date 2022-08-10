package com.example.demo.servletsBook;

import com.example.demo.Book;
import com.example.demo.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String country = request.getParameter("country");
        int year= Integer.parseInt(request.getParameter("year"));
        int amount=Integer.parseInt(request.getParameter("amount"));
        if (amount<0){
            response.sendError(404, "Wrong role for user!!!");
            return;
        }
        if (year < 2022){
            response.sendError(404, "Wrong role for user!!!");
            return;
        }
        Book myBook = new Book();

        myBook.setName(name);
        myBook.setAuthor(author);
        myBook.setCountry(country);
        myBook.setAmount(amount);
        myBook.setYear(year);

        out.println(myBook);
        int status = BookRepository.save(myBook);


        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }
}

