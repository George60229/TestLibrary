package com.example.demo.servletsUser;

import com.example.demo.BookRepository;
import com.example.demo.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        if(id<0){
            response.sendError(404, "Wrong id !!!");
            return;
        }
        UserRepository.delete(id);
        response.sendRedirect("viewUserServlet");//todo useful this code
    }
}
