package com.example.final_module_3.controllers;

import com.example.final_module_3.entity.Room;
import com.example.final_module_3.entity.PaymentType;
import com.example.final_module_3.services.RoomService;
import com.example.final_module_3.services.PaymentTypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RoomController", urlPatterns = {"/rooms/*"})
public class RoomController extends HttpServlet {
    private RoomService roomService;
    private PaymentTypeService paymentTypeService;

    @Override
    public void init() throws ServletException {
        this.roomService = new RoomService();
        this.paymentTypeService = new PaymentTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListRooms(req, resp);
            }

            switch (url) {
                case "/delete":
                    this.roomService.deleteRoom(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                case "/create":
                    this.renderPageCreate(req, resp);
                    break;
                case "/search":
                    this.renderSearchPage(req, resp);
                    break;
                case "/view":
                    this.renderRoomDetails(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (url) {
                case "/create":
                    this.roomService.createRoom(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderListRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Room> rooms = this.roomService.getAllRooms(request);
        request.setAttribute("rooms", rooms);
        int totalRooms = this.roomService.getRoomCount();
        request.setAttribute("totalRooms", totalRooms);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<PaymentType> paymentTypes = this.paymentTypeService.getAllPaymentTypes();
        request.setAttribute("paymentTypes", paymentTypes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Room> rooms = this.roomService.searchRooms(request);
        request.setAttribute("rooms", rooms);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/list.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void renderRoomDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Room room = this.roomService.findById(request);
        request.setAttribute("room", room);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/rooms/view.jsp");
        requestDispatcher.forward(request, response);
    }
    private boolean validateRoomInput(HttpServletRequest req) {
        String tenantName = req.getParameter("tenantName");
        String phoneNumber = req.getParameter("phoneNumber");
        String startDate = req.getParameter("startDate");
        String paymentType = req.getParameter("paymentType");
        String notes = req.getParameter("notes");

        if (tenantName == null || !tenantName.matches("^[a-zA-Z\\s]{5,50}$")) {
            req.setAttribute("errorMessage", "Tên người thuê trọ không hợp lệ.");
            return false;
        }

        if (phoneNumber == null || !phoneNumber.matches("^\\d{10}$")) {
            req.setAttribute("errorMessage", "Số điện thoại không hợp lệ.");
            return false;
        }

        if (startDate == null || !startDate.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            req.setAttribute("errorMessage", "Ngày bắt đầu thuê không hợp lệ.");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDateParsed = sdf.parse(startDate);
            if (startDateParsed.before(new Date())) {
                req.setAttribute("errorMessage", "Ngày bắt đầu thuê không được là ngày quá khứ.");
                return false;
            }
        } catch (ParseException e) {
            req.setAttribute("errorMessage", "Định dạng ngày không hợp lệ.");
            return false;
        }

        if (paymentType == null || !Arrays.asList("monthly", "quarterly", "yearly").contains(paymentType)) {
            req.setAttribute("errorMessage", "Hình thức thanh toán không hợp lệ.");
            return false;
        }

        if (notes != null && notes.length() > 200) {
            req.setAttribute("errorMessage", "Ghi chú không được vượt quá 200 ký tự.");
            return false;
        }

        return true;
    }

}
