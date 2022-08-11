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

@WebServlet("/blockUserServlet")
public class BlockUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        User myUser = new User();

        myUser.setBlocked(true);
        myUser.setId(id);


        int status = UserRepository.update(myUser);

        if (status > 0) {
            response.sendRedirect("viewUserServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}


