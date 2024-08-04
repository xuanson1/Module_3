package com.example.case_module_3.services;

import com.example.case_module_3.entity.Category;
import com.example.case_module_3.entity.Employee;
import com.example.case_module_3.models.EmployeeModel;

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

public class EmployeeService {
    private EmployeeModel employeeModel;

    public EmployeeService() {
        this.employeeModel = new EmployeeModel();
    }

    public List<Employee> getAllEmployees(HttpServletRequest request) throws SQLException {

        List<Employee> employees = new ArrayList<>();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        ResultSet resultSet = null;

        if (page == null || limit == null) {
            resultSet = this.employeeModel.getEmployees();
        } else {
            int intPage = Integer.parseInt(page);
            int intLimit = Integer.parseInt(limit);
            int offset = (intPage - 1) * intLimit;
            resultSet = this.employeeModel.getEmployeesByOffset(offset, intLimit);
        }

        while (resultSet.next()) {
            int employeeId = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate hireDate = resultSet.getDate("hire_date").toLocalDate();
            double salary = resultSet.getDouble("salary");
            int categoryId = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");

            Employee employee = new Employee(firstName, lastName, dateOfBirth, email, phoneNumber, hireDate, salary, categoryId);
            employee.setEmployeeId(employeeId);

            Category category = new Category(categoryName);
            category.setCategoryId(categoryId);

            employee.setCategory(category);
            employees.add(employee);
        }
        return employees;
    }

    public int getEmployeeCount() throws SQLException {
        ResultSet resultSet = this.employeeModel.getEmployees();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.employeeModel.destroyEmployee(id);
    }

    public void createEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String hireDateStr = request.getParameter("hireDate");
        String salaryStr = request.getParameter("salary");
        String categoryIdStr = request.getParameter("category_id");


        try {
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
            LocalDate hireDate = LocalDate.parse(hireDateStr);
            double salary = Double.parseDouble(salaryStr);
            int categoryId = Integer.parseInt(categoryIdStr);

            Employee employee = new Employee(firstName, lastName, dateOfBirth, email, phoneNumber, hireDate, salary, categoryId);
            this.employeeModel.store(employee);
        } catch (DateTimeParseException e) {
            throw new ServletException("Invalid date format. Please use the format YYYY-MM-DD.", e);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format for salary or category ID.", e);
        }
    }

    public List<Employee> searchEmployees(HttpServletRequest request) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.employeeModel.search(keyword);
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            int employeeId = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate hireDate = resultSet.getDate("hire_date").toLocalDate();
            double salary = resultSet.getDouble("salary");
            int categoryId = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");

            Employee employee = new Employee(firstName, lastName, dateOfBirth, email, phoneNumber, hireDate, salary, categoryId);
            employee.setEmployeeId(employeeId);

            Category category = new Category(categoryName);
            category.setCategoryId(categoryId);

            employee.setCategory(category);
            employees.add(employee);
        }
        return employees;
    }


    public Employee findById(HttpServletRequest request) throws SQLException {
        String employeeIdParam = request.getParameter("id");

        if (employeeIdParam == null || employeeIdParam.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID is missing.");
        }

        int id;
        try {
            id = Integer.parseInt(employeeIdParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Employee ID format.", e);
        }

        ResultSet resultSet = this.employeeModel.getEmployeeById(id);
        Employee employee = null;

        if (resultSet.next()) {
            int idEmployee = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate hireDate = resultSet.getDate("hire_date").toLocalDate();
            double salary = resultSet.getDouble("salary");
            int categoryId = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");

            employee = new Employee(firstName, lastName, dateOfBirth, email, phoneNumber, hireDate, salary, categoryId);
            employee.setEmployeeId(idEmployee);

            Category category = new Category(categoryName);
            category.setCategoryId(categoryId);

            employee.setCategory(category);
        }

        return employee;
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("date_of_birth"));
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        LocalDate hireDate = LocalDate.parse(request.getParameter("hire_date"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        Employee employee = new Employee(firstName, lastName, dateOfBirth, email, phoneNumber, hireDate, salary, categoryId);
        employee.setEmployeeId(employeeId);

        this.employeeModel.update(employee);
    }
}
