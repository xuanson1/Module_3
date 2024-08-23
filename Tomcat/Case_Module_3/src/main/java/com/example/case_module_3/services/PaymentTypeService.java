package com.example.case_module_3.services;

import com.example.case_module_3.entity.PaymentType;
import com.example.case_module_3.models.PaymentTypeModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ResultSet resultSet = this.paymentTypeModel.getAllPaymentTypes();
        while (resultSet.next()) {
            int id = resultSet.getInt("paymenttype_id");
            String name = resultSet.getString("paymenttype_name");
            PaymentType paymentType = new PaymentType(name);
            paymentType.setPaymentTypeId(id);
            paymentTypes.add(paymentType);
        }
        return paymentTypes;
    }

    public void deletePaymentType(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.paymentTypeModel.delete(id);
    }

    public void createPaymentType(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        System.out.println("PaymentType name received: " + name);

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("PaymentType name cannot be null or empty");
        }
        PaymentType paymentType = new PaymentType(name);
        this.paymentTypeModel.store(paymentType);
    }


    public PaymentType findByIdPaymentType(HttpServletRequest request) throws SQLException {
        String paymentTypeIdParam = request.getParameter("id");

        if (paymentTypeIdParam == null || paymentTypeIdParam.trim().isEmpty()) {
            throw new IllegalArgumentException("PaymentType ID is missing.");
        }

        int id;
        try {
            id = Integer.parseInt(paymentTypeIdParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid PaymentType ID format.", e);
        }

        ResultSet resultSet = this.paymentTypeModel.getPaymentTypeById(id);
        PaymentType paymentType = null;

        if (resultSet.next()) {
            int idPaymentType = resultSet.getInt("paymenttype_id");
            String name = resultSet.getString("paymenttype_name");

            paymentType = new PaymentType(name);
            paymentType.setPaymentTypeId(idPaymentType);
        }

        return paymentType;
    }

    public List<PaymentType> searchPaymentTypes(HttpServletRequest request) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.paymentTypeModel.searchPaymentType(keyword);
        List<PaymentType> paymentTypes = new ArrayList<>();
        while (resultSet.next()) {
            int paymentTypeId = resultSet.getInt("paymenttype_id");
            String paymentTypeName = resultSet.getString("paymenttype_name");

            PaymentType paymentType = new PaymentType(paymentTypeName);
            paymentType.setPaymentTypeId(paymentTypeId);

            paymentTypes.add(paymentType);
        }
        return paymentTypes;
    }
}
