package com.example.final_module_3.controllers;

import com.example.final_module_3.entity.PaymentType;
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

@WebServlet(name = "PaymentTypeController", urlPatterns = {"/paymenttypes/*"})
public class PaymentTypeController extends HttpServlet {
    private PaymentTypeService paymentTypeService;

    @Override
    public void init() throws ServletException {
        this.paymentTypeService = new PaymentTypeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListPaymentTypes(req, resp);
            } else {
                switch (url) {
                    case "/list":
                        this.renderListPaymentTypes(req, resp);
                        break;
                    case "/delete":
                        this.paymentTypeService.deletePaymentType(req, resp);
                        resp.sendRedirect(req.getContextPath() + "/paymenttypes/list");
                        break;
                    case "/create":
                        this.renderPageCreate(req, resp);
                        break;
                    default:
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if ("/create".equals(url)) {
                this.paymentTypeService.createPaymentType(req, resp);
                resp.sendRedirect(req.getContextPath() + "/paymenttypes/list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderListPaymentTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<PaymentType> paymentTypes = this.paymentTypeService.getAllPaymentTypes();
        request.setAttribute("paymentTypes", paymentTypes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/paymenttypes/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/paymenttypes/create.jsp");
        requestDispatcher.forward(request, response);
    }
}
