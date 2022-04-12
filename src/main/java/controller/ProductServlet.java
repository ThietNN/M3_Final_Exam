package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    public void init(){
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    productListForm(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "create":
                    insertProduct(request,response);
                    break;
                case "update":
                    updateProduct(request,response);
                    break;
                case "findById":
                    findProductById(request,response);
                    break;
                case "findByName":
                    findByName(request,response);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }
    private void findProductById(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("searchId"));
        Product product = productDAO.findById(id);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
    private void findByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        String name = request.getParameter("searchName");
        Product product = productDAO.findByName(name);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }


    private void productListForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Product> productList = productDAO.findAll();
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Category> categoryList = categoryDAO.findAll();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request,response);
    }
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categoryList = categoryDAO.findAll();
        request.setAttribute("categoryList", categoryList);
        Product product = productDAO.findById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/update.jsp");
        request.setAttribute("product",product);
        requestDispatcher.forward(request,response);
    }
    private void insertProduct (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryDAO.findById(categoryId);
        Product product = new Product(name,price,color,description,category);
        productDAO.save(product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
    private void updateProduct (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryDAO.findById(categoryId);
        Product product = new Product(id,name,price,color,description,category);
        productDAO.update(product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.delete(id);
        List<Product> productList = productDAO.findAll();
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        requestDispatcher.forward(request,response);
    }
}
