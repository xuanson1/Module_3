package com.example.case_module_3.services;

import com.example.case_module_3.entity.PaymentType;
import com.example.case_module_3.entity.Room;
import com.example.case_module_3.models.RoomModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private RoomModel roomModel;

    public RoomService() {
        this.roomModel = new RoomModel();
    }

    public List<Room> getAllRooms(HttpServletRequest request) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        ResultSet resultSet = null;

        if (page == null || limit == null) {
            resultSet = this.roomModel.getRooms();
        } else {
            int intPage = Integer.parseInt(page);
            int intLimit = Integer.parseInt(limit);
            int offset = (intPage - 1) * intLimit;
            resultSet = this.roomModel.getRoomsByOffset(offset, intLimit);
        }

        while (resultSet.next()) {
            int roomId = resultSet.getInt("room_id");
            String tenantName = resultSet.getString("tenant_name");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
            int paymentTypeId = resultSet.getInt("paymenttype_id");
            String notes = resultSet.getString("notes");
            String paymentTypeName = resultSet.getString("paymenttype_name");

            PaymentType paymentType = new PaymentType(paymentTypeId, paymentTypeName);
            Room room = new Room(tenantName, phoneNumber, startDate, paymentTypeId, notes, paymentType);
            room.setRoomId(roomId);

            paymentType.setPaymentTypeId(paymentTypeId);

            room.setPaymentType(paymentType);
            rooms.add(room);
        }
        return rooms;
    }

    public int getRoomCount() throws SQLException {
        ResultSet resultSet = this.roomModel.getRooms();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }

    public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.roomModel.destroyRoom(id);
    }

    public void createRoom(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        String startDateStr = request.getParameter("startDate");
        String paymentTypeIdStr = request.getParameter("paymentTypeId");
        String notes = request.getParameter("notes");

        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            int paymentTypeId = Integer.parseInt(paymentTypeIdStr);

            String paymentTypeName = "";
            PaymentType paymentType = new PaymentType(paymentTypeId, paymentTypeName);
            Room room = new Room(tenantName, phoneNumber, startDate, paymentTypeId, notes, paymentType);
            this.roomModel.store(room);
        } catch (DateTimeParseException e) {
            throw new ServletException("Invalid date format. Please use the format YYYY-MM-DD.", e);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format for payment type ID.", e);
        }
    }

    public List<Room> searchRooms(HttpServletRequest request) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.roomModel.search(keyword);
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            int roomId = resultSet.getInt("room_id");
            String tenantName = resultSet.getString("tenant_name");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
            int paymentTypeId = resultSet.getInt("paymenttype_id");
            String notes = resultSet.getString("notes");
            String paymentTypeName = resultSet.getString("paymenttype_name");

            PaymentType paymentType = new PaymentType(paymentTypeId, paymentTypeName);
            Room room = new Room(tenantName, phoneNumber, startDate, paymentTypeId, notes, paymentType);
            room.setRoomId(roomId);

            paymentType.setPaymentTypeId(paymentTypeId);

            room.setPaymentType(paymentType);
            rooms.add(room);
        }
        return rooms;
    }

    public Room findById(HttpServletRequest request) throws SQLException {
        String roomIdParam = request.getParameter("id");

        if (roomIdParam == null || roomIdParam.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID is missing.");
        }

        int id;
        try {
            id = Integer.parseInt(roomIdParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Room ID format.", e);
        }

        ResultSet resultSet = this.roomModel.getRoomById(id);
        Room room = null;

        if (resultSet.next()) {
            int roomId = resultSet.getInt("room_id");
            String tenantName = resultSet.getString("tenant_name");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
            int paymentTypeId = resultSet.getInt("paymenttype_id");
            String notes = resultSet.getString("notes");
            String paymentTypeName = resultSet.getString("paymenttype_name");

            PaymentType paymentType = new PaymentType(paymentTypeId, paymentTypeName);
            room = new Room(tenantName, phoneNumber, startDate, paymentTypeId, notes, paymentType);
            room.setRoomId(roomId);

            paymentType.setPaymentTypeId(paymentTypeId);

            room.setPaymentType(paymentType);
        }

        return room;
    }
}
