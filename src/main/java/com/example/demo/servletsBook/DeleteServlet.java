package com.example.demo.servletsBook;

import com.example.demo.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        if(id<0){
            response.sendError(404, "Wrong id !!!");
            return;
        }
        BookRepository.delete(id);
        response.sendRedirect("viewServlet");
    }
}

