package DAO;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final String FIND_ALL = "select p.id, p.name, p.price, p.color, p.description, c.id as categoryId , c.name as category from product p join category c on p.category_ID = c.id;";
    private static final String FIND_BY_ID = "select p.id,  p.name, p.price, p.color, p.description, c.name as catetory from product p join category c on p.category_ID = c.id where p.id = ?;";
    private static final String FIND_BY_NAME = "select p.id, p.name, p.price, p.color, p.description, c.name as category from product p join category c on p.category_ID = c.id where p.name = ?;";
    private static final String DELETE_BY_ID = "delete from product where id = ?;";
    private static final String INSERT = "insert into product(name,price,color,description,category_ID) value (?, ?, ?, ?, ?);";
    private static final String UPDATE = "update product set name = ?, price = ?, color = ?, description = ?, category_ID = ? where id = ?;";
    Connection connection = SingletonConnection.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("category");
                Category category = new Category(categoryId, categoryName);
                Product product = new Product(id, name, price, color, description, category);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        System.out.println(productDAO.findAll());
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category");
                Category category = new Category(categoryName);
                product = new Product(id, name, price, color, description, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getColor());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Product product) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getColor());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.setInt(6, product.getId());
            rowUpdated = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public Product findByName(String name) {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int price = resultSet.getInt("price");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category");
                Category category = new Category(categoryName);
                product = new Product(id,name,price,color,description,category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
}