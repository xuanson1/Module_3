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
import java.util.List;

@WebServlet(name = "RoomController", urlPatterns = {"/rooms/*"})
public class RoomController extends HttpServlet {
    private RoomService roomService;
    private PaymentTypeService paymentTypeService;

    @Override
    public void init() throws ServletException {
        try {
            this.roomService = new RoomService();
            this.paymentTypeService = new PaymentTypeService();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize services", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();

        try {
            switch (url) {
                case "/list":
                    this.renderListRooms(req, resp);
                    break;
                case "/delete":
                    this.roomService.deleteRoom(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                case "/deleteMultiple":
                    this.roomService.deleteMultipleRooms(req, resp);
                    resp.sendRedirect("/rooms");
                    break;
                case "/create":
                    this.renderPageCreate(req, resp);
                    break;
                case "/search":
                    this.renderSearchPage(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error.", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if ("/deleteMultiple".equals(url)) {
                this.roomService.deleteMultipleRooms(req, resp);
                resp.sendRedirect("/rooms/list");
            } else if ("/create".equals(url)) {
                this.roomService.createRoom(req, resp);
                resp.sendRedirect("/rooms/list");
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request.", e);
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
}
