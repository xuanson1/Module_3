package com.example.final_module_3.models;

import com.example.final_module_3.databases.DatabaseConnect;
import com.example.final_module_3.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class RoomModel {
    private Connection conn;

    public RoomModel() {
        this.conn = DatabaseConnect.getConnection();
        if (this.conn == null) {
            throw new IllegalStateException("Database connection is null");
        }
    }

    public ResultSet getRooms() throws SQLException {
        String sql = "SELECT r.*, pt.paymentTypeName FROM room r JOIN paymentType pt ON r.paymentTypeId = pt.paymentTypeId";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getRoomsByOffset(int offset, int limit) throws SQLException {
        String sql = "SELECT r.*, pt.paymentTypeName FROM room r JOIN paymentType pt ON r.paymentTypeId = pt.paymentTypeId LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);
        return preparedStatement.executeQuery();
    }


    public void destroyRoom(int id) throws SQLException {
        String sql = "DELETE FROM room WHERE roomId = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void store(Room room) throws SQLException {
        String sql = "INSERT INTO room (tenantName, phoneNumber, startDate, paymentTypeId, notes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, room.getTenantName());
        preparedStatement.setString(2, room.getPhoneNumber());
        preparedStatement.setDate(3, java.sql.Date.valueOf(room.getStartDate()));
        preparedStatement.setInt(4, room.getPaymentTypeId());
        preparedStatement.setString(5, room.getNotes());
        preparedStatement.execute();
    }


    public ResultSet search(String keyword) throws SQLException {
        String sql = "SELECT r.*, pt.paymentTypeName FROM room r JOIN paymentType pt ON r.paymentTypeId = pt.paymentTypeId WHERE r.tenantName LIKE ? OR r.phoneNumber LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + keyword + "%");
        preparedStatement.setString(2, "%" + keyword + "%");
        return preparedStatement.executeQuery();
    }

    public ResultSet getRoomById(int id) throws SQLException {
        String sql = "SELECT r.*, pt.paymentTypeName FROM room r JOIN paymentType pt ON r.paymentTypeId = pt.paymentTypeId WHERE r.roomId = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void deleteMultipleRooms(List<Integer> roomIds) throws SQLException {
        if (roomIds == null || roomIds.isEmpty()) {
            return;
        }

        StringBuilder sql = new StringBuilder("DELETE FROM room WHERE roomId IN (");
        for (int i = 0; i < roomIds.size(); i++) {
            sql.append("?");
            if (i < roomIds.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < roomIds.size(); i++) {
                preparedStatement.setInt(i + 1, roomIds.get(i));
            }
            preparedStatement.executeUpdate();
        }
    }

}