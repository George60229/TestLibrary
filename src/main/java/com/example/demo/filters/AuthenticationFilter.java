package com.example.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log(">>> AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        this.context.log("Requested Resource::http://localhost:8080" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && !(uri.endsWith("liba/saveServlet") || uri.endsWith("liba/loginServlet") || uri.endsWith("liba/viewServlet")||uri.endsWith("liba/viewByIDServlet")||uri.endsWith("liba/viewByAuthorServlet")||uri.endsWith("liba/viewServletOrderByYear")||uri.endsWith("liba/viewServletOrderByCountry")||uri.endsWith("liba/viewServletOrderByName")||uri.endsWith("liba/viewServletOrderByAuthor")||uri.endsWith("liba/saveUserServlet")||uri.endsWith("liba/viewUserServlet")||uri.endsWith("liba/deleteUserServlet")||uri.endsWith("liba/blockUserServlet")||uri.endsWith("liba/putServlet"))) {
            this.context.log("<<< Unauthorized access request");
            PrintWriter out = res.getWriter();
            out.println("No access!!!");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }
}
