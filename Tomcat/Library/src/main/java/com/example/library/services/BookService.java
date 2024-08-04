package com.example.library.services;

import com.example.library.entity.Book;
import com.example.library.models.BookModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookModel bookModel;
    public BookService(){
        this.bookModel = new BookModel();
    }

    public void renderListBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Book> books = this.bookModel.getBooks();
        req.setAttribute("books",books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/listBooks.jsp");
        requestDispatcher.forward(req,resp);
    }
}
