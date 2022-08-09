package com.example.demo.servletsUser;
import com.example.demo.User;
import com.example.demo.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveUserServlet")
public class SaveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        User myUser= new User();

        myUser.setLogin(login);
        myUser.setPassword(password);
        myUser.setRole(role);


        out.println(myUser);
        int status = UserRepository.save(myUser);


        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }
}
