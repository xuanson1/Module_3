package com.example.library.controllers;

import com.example.library.entity.Book;
import com.example.library.models.BookModel;
import com.example.library.services.BookService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="BookController",urlPatterns = {"/books/*"})
public class BookController extends HttpServlet {
    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try{
            if(url == null || url.equals("/")){
                this.bookService.renderListBooks(req,resp);
            }
        }catch(SQLException){

        }

    }

}
