package com.example.final_module_3.services;

import com.example.final_module_3.entity.PaymentType;
import com.example.final_module_3.models.PaymentTypeModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentTypeService {
    private PaymentTypeModel paymentTypeModel;

    public PaymentTypeService() {
        this.paymentTypeModel = new PaymentTypeModel();
    }

    public List<PaymentType> getAllPaymentTypes() throws SQLException {
        List<PaymentType> paymentTypes = new ArrayList<>();
        ResultSet resultSet = this.paymentTypeModel.getPaymentTypes();
        while (resultSet.next()) {
            int paymentTypeId = resultSet.getInt("paymentTypeId");
            String paymentTypeName = resultSet.getString("paymentTypeName");

            PaymentType paymentType = new PaymentType(paymentTypeName);
            paymentType.setPaymentTypeId(paymentTypeId);
            paymentTypes.add(paymentType);
        }
        return paymentTypes;
    }

    public void createPaymentType(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String paymentTypeName = request.getParameter("paymentTypeName");
        PaymentType paymentType = new PaymentType(paymentTypeName);
        this.paymentTypeModel.store(paymentType);
    }

    public void deletePaymentType(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.paymentTypeModel.destroyPaymentType(id);
    }

    public PaymentType findById(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        ResultSet resultSet = this.paymentTypeModel.getPaymentTypeById(id);
        PaymentType paymentType = null;
        if (resultSet.next()) {
            String paymentTypeName = resultSet.getString("paymentTypeName");
            paymentType = new PaymentType(paymentTypeName);
            paymentType.setPaymentTypeId(id);
        }
        return paymentType;
    }
}
