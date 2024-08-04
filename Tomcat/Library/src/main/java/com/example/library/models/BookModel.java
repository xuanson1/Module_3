package com.example.library.models;

import com.example.library.databases.DatabaseConnect;
import com.example.library.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BookModel {
    private Connection conn;
    public BookModel(){
        this.conn = DatabaseConnect.getConnection();
    }

    public List<Book> getBooks() throws SQLException {
        String sql = "select * from books";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int category_id = resultSet.getInt("category_id");
            Book book = new Book(id, name, description, price, category_id);
            books.add(book);
        }
        return books;
    }

}
