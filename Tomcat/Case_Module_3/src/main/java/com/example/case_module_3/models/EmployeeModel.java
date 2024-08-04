package com.example.case_module_3.models;

import com.example.case_module_3.databases.DatabaseConnect;
import com.example.case_module_3.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class EmployeeModel {
    private Connection conn;

    public EmployeeModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getEmployees() throws SQLException {
        String sql = "SELECT e.*, c.category_name FROM employee e JOIN employee_category c ON e.category_id = c.category_id";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getEmployeesByOffset(int offset, int limit) throws SQLException {
        String sql = "SELECT employee.*, employee_category.category_name as category_name " +
                "FROM employee " +
                "JOIN employee_category ON employee.category_id = employee_category.category_id " +
                "LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);
        return preparedStatement.executeQuery();
    }

    public void destroyEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void store(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(first_name, last_name, date_of_birth, email, phone_number, hire_date, salary, category_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDateOfBirth()));
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, employee.getPhoneNumber());
        preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getHireDate()));
        preparedStatement.setDouble(7, employee.getSalary());
        preparedStatement.setInt(8, employee.getCategoryId());
        preparedStatement.execute();
    }

    public ResultSet search(String name) throws SQLException {
        String sql = "SELECT employee.*, employee_category.category_name as category_name " +
                "FROM employee " +
                "JOIN employee_category ON employee.category_id = employee_category.category_id " +
                "WHERE employee.first_name LIKE ? OR employee.last_name LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, "%" + name + "%");
        return preparedStatement.executeQuery();
    }

    public ResultSet getEmployeeById(int id) throws SQLException {
        String sql = "SELECT employee.*, employee_category.category_name as category_name " +
                "FROM employee " +
                "JOIN employee_category ON employee.category_id = employee_category.category_id " +
                "WHERE employee.employee_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ?, " +
                "hire_date = ?, salary = ?, category_id = ? WHERE employee_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(employee.getDateOfBirth()));
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, employee.getPhoneNumber());
        preparedStatement.setDate(6, java.sql.Date.valueOf(employee.getHireDate()));
        preparedStatement.setDouble(7, employee.getSalary());
        preparedStatement.setInt(8, employee.getCategoryId());
        preparedStatement.setInt(9, employee.getEmployeeId());
        preparedStatement.execute();
    }
}
