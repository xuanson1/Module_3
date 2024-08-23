package com.example.final_module_3.models;

import com.example.final_module_3.databases.DatabaseConnect;
import com.example.final_module_3.entity.PaymentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTypeModel {
    private Connection conn;

    public PaymentTypeModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getPaymentTypes() throws SQLException {
        String sql = "SELECT * FROM paymenttype";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void store(PaymentType paymentType) throws SQLException {
        String sql = "INSERT INTO paymenttype(payment_type_name) VALUES (?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, paymentType.getPaymentTypeName());
        preparedStatement.execute();
    }

    public void destroyPaymentType(int id) throws SQLException {
        String sql = "DELETE FROM paymenttype WHERE payment_type_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public ResultSet getPaymentTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM paymenttype WHERE payment_type_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}
