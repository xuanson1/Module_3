package com.example.case_module_3.services;

import com.example.case_module_3.entity.Category;
import com.example.case_module_3.models.CategoryModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private CategoryModel categoryModel;

    public CategoryService() {
        this.categoryModel = new CategoryModel();
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        ResultSet resultSet = this.categoryModel.getAllCategories();
        while (resultSet.next()){
            int id = resultSet.getInt("category_id");
            String name = resultSet.getString("category_name");
            Category category = new Category(name);
            category.setCategoryId(id);
            categories.add(category);
        }
        return categories;
    }

    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.categoryModel.delete(id);
    }
    public void createCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        System.out.println("Category name received: " + name);

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        Category category = new Category(name);
        this.categoryModel.store(category);
    }
    public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("category_name");

        Category category = new Category(name);
        category.setCategoryId(categoryId);

        this.categoryModel.update(category);
    }

    public Category findByIdCategory(HttpServletRequest request) throws SQLException {
        String categoryIdParam = request.getParameter("id");

        if (categoryIdParam == null || categoryIdParam.trim().isEmpty()) {
            throw new IllegalArgumentException("Category ID is missing.");
        }

        int id;
        try {
            id = Integer.parseInt(categoryIdParam);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Category ID format.", e);
        }

        ResultSet resultSet = this.categoryModel.getCategoryById(id);
        Category category = null;

        if (resultSet.next()) {
            int idCategory = resultSet.getInt("category_id");
            String name = resultSet.getString("category_name");

            category = new Category(name);
            category.setCategoryId(idCategory);
        }

        return category;
    }
    public List<Category> searchCategories(HttpServletRequest request) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.categoryModel.searchCategory(keyword);
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            int categoryId = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");

            Category category = new Category(categoryName);
            category.setCategoryId(categoryId);

            categories.add(category);
        }
        return categories;
    }

}
