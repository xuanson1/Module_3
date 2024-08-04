package com.example.case_module_3.controllers;

import com.example.case_module_3.entity.Employee;
import com.example.case_module_3.entity.Category;
import com.example.case_module_3.services.EmployeeService;
import com.example.case_module_3.services.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "EmployeeController", urlPatterns = {"/employees/*"})
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService ;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.employeeService = new EmployeeService();
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListEmployees(req, resp);
            }

            switch (url) {
                case "/delete":
                    this.employeeService.deleteEmployee(req, resp);
                    resp.sendRedirect("/employees");
                    break;
                case "/create":
                    this.renderPageCreate(req, resp);
                    break;
                case "/search":
                    this.renderSearchPage(req, resp);
                    break;
                case "/update":
                    this.renderPageUpdate(req, resp);
                    break;
                default:
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                try {
                    this.employeeService.createEmployee(req, resp);
                    resp.sendRedirect("/employees");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":
                try {
                    this.employeeService.updateEmployee(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/employees");
                break;
        }
    }

    public void renderListEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Employee> employees = this.employeeService.getAllEmployees(request);
        request.setAttribute("employees", employees);
        int totalEmployees = this.employeeService.getEmployeeCount();
        request.setAttribute("totalEmployees", totalEmployees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/list.jsp");
        requestDispatcher.forward(request, response);
    }


    public void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Employee> employees = this.employeeService.searchEmployees(request);
        request.setAttribute("employees", employees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/list.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void renderPageUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Employee employee = this.employeeService.findById(request);
        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("employee", employee);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/employees/update.jsp");
        requestDispatcher.forward(request, response);
    }


}
