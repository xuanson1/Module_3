package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //tao gia tri ban dau, ketnoi csdl
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        PrintWriter writer = resp.getWriter();
//        writer.println("<form action=\"/login\" method=\"post\">");
//        writer.println("<label for=\"email\">email:</label>");
//        writer.println("<input type=\"text\" name=\"email\">");
//        writer.println("<label for=\"password\">Password:</label>");
//        writer.println("<input type=\"password\" name=\"password\">");
//        writer.println("<input type=\"submit\" value=\"Login\">");
//        writer.println("</form>");
//        writer.close();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email.equals("admin@gmail.com") && password.equals("1234")) {
            resp.sendRedirect("dashboard");
        }else{
            resp.sendRedirect("login");
        }
    }
    @Override
    public void destroy() {
        super.destroy();
    }


}
