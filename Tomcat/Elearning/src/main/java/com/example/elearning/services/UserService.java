package com.example.elearning.services;

import com.example.elearning.entity.User;
import com.example.elearning.models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserService {
    private UserModel userModel;

    public UserService(){
        userModel = new UserModel();
    }
    public void renderPageUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userModel.getListUsers();
        req.setAttribute("users", users);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/users/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
