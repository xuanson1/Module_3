package com.example.case_module_3.models;

import com.example.case_module_3.databases.DatabaseConnect;
import com.example.case_module_3.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryModel {
    private Connection conn;

    public CategoryModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getAllCategories() throws SQLException {
        String sql = "SELECT * FROM employee_category";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void delete(int categoryId) throws SQLException {
        String sql = "DELETE FROM employee_category WHERE category_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, categoryId);
            statement.executeUpdate();

    }
    public ResultSet getCategoryById(int id) throws SQLException {
        String sql = "SELECT * FROM employee_category WHERE category_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void store(Category category) throws SQLException {
        String sql = "INSERT INTO employee_category(category_name) VALUES (?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.execute();
    }
    public void update(Category category) throws SQLException {
        String sql = "UPDATE employee_category SET category_name = ? WHERE category_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.setInt(2, category.getCategoryId());
        preparedStatement.execute();
    }
    public ResultSet searchCategory(String name) throws SQLException {
        String sql = "SELECT * FROM employee_category WHERE category_name LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        return preparedStatement.executeQuery();
    }


}
