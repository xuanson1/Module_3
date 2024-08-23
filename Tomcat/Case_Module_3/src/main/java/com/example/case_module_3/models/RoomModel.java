package com.example.case_module_3.models;

import com.example.case_module_3.databases.DatabaseConnect;
import com.example.case_module_3.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomModel {
    private Connection conn;

    public RoomModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getRooms() throws SQLException {
        String sql = "SELECT r.*, p.paymenttype_name FROM rooms r JOIN paymenttype p ON r.paymenttype_id = p.paymenttype_id";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getRoomsByOffset(int offset, int limit) throws SQLException {
        String sql = "SELECT r.*, p.paymenttype_name FROM rooms r JOIN paymenttype p ON r.paymenttype_id = p.paymenttype_id LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);
        return preparedStatement.executeQuery();
    }

    public void destroyRoom(int id) throws SQLException {
        String sql = "DELETE FROM rooms WHERE room_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void store(Room room) throws SQLException {
        String sql = "INSERT INTO rooms(tenant_name, phone_number, start_date, paymenttype_id, notes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, room.getTenantName());
        preparedStatement.setString(2, room.getPhoneNumber());
        preparedStatement.setDate(3, java.sql.Date.valueOf(room.getStarDate()));
        preparedStatement.setInt(4, room.getPaymentTypeId());
        preparedStatement.setString(5, room.getNotes());
        preparedStatement.execute();
    }

    public ResultSet search(String tenantName) throws SQLException {
        String sql = "SELECT r.*, p.paymenttype_name FROM rooms r JOIN paymenttype p ON r.paymenttype_id = p.paymenttype_id WHERE r.tenant_name LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + tenantName + "%");
        return preparedStatement.executeQuery();
    }

    public ResultSet getRoomById(int id) throws SQLException {
        String sql = "SELECT r.*, p.paymenttype_name FROM rooms r JOIN paymenttype p ON r.paymenttype_id = p.paymenttype_id WHERE r.room_id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }


}
