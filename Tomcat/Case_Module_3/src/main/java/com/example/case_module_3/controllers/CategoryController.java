package com.example.case_module_3.controllers;

import com.example.case_module_3.entity.Category;
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

@WebServlet(name = "CategoryController", urlPatterns = {"/categories/*"})
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListCategories(req, resp);
            }

            switch (url) {
                case "/delete":
                    this.categoryService.deleteCategory(req, resp);
                    resp.sendRedirect("/categories");
                    break;
                case "/create":
                    this.renderPageCreate(req, resp);
                    break;
                case "/search":
                    this.renderSearchCategory(req, resp);
                    break;
                case "/update":
                    this.renderPageUpdate(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (url) {
                case "/create":
                    this.categoryService.createCategory(req,resp);
                    resp.sendRedirect("/categories");
                    break;
                case "/update":
                    this.categoryService.updateCategory(req,resp);
                    resp.sendRedirect("/categories");
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderListCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/create.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void renderPageUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int categoryId = Integer.parseInt(request.getParameter("id"));

        Category category = this.categoryService.findByIdCategory(request);

        List<Category> categories = this.categoryService.getAllCategories();

        request.setAttribute("category", category);
        request.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/update.jsp");
        requestDispatcher.forward(request, response);
    }
    public void renderSearchCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Category> categories = this.categoryService.searchCategories(request);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/list.jsp");
        requestDispatcher.forward(request, response);
    }



}
