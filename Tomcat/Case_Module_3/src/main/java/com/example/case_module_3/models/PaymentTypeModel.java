package com.example.case_module_3.models;

import com.example.case_module_3.databases.DatabaseConnect;
import com.example.case_module_3.entity.PaymentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTypeModel {
    private Connection conn;

    public PaymentTypeModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getAllPaymentTypes() throws SQLException {
        String sql = "SELECT * FROM paymenttype";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void delete(int paymentTypeId) throws SQLException {
        String sql = "DELETE FROM paymenttype WHERE paymenttype_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, paymentTypeId);
        statement.executeUpdate();
    }

    public ResultSet getPaymentTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM paymenttype WHERE paymenttype_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void store(PaymentType paymentType) throws SQLException {
        String sql = "INSERT INTO paymenttype(paymenttype_name) VALUES (?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, paymentType.getPaymentTypeName());
        preparedStatement.execute();
    }



    public ResultSet searchPaymentType(String name) throws SQLException {
        String sql = "SELECT * FROM paymenttype WHERE paymenttype_name LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        return preparedStatement.executeQuery();
    }
}
