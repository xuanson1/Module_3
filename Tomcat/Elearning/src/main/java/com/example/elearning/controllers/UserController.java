package com.example.elearning.controllers;

import com.example.elearning.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="UserController", urlPatterns = {"/users/*"})
public class UserController extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        if(url==null){
            this.userService.renderPageUserList(req, resp);
            return;
        }
        switch (url) {


            default:
        }
    }
}
