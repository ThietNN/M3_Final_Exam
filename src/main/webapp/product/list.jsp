<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Manager</title>
</head>
<body>
    <h1>Product Management</h1>
    <h2>
        <a href="/product?action=create">Add New Product</a>
    </h2>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Product List: </h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Color</th>
            <th>Description</th>
            <th>Category</th>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td><c:out value="${product.getId()}"/></td>
                <td><c:out value="${product.getName()}"/></td>
                <td><c:out value="${product.getPrice()}"/></td>
                <td><c:out value="${product.getColor()}"/></td>
                <td><c:out value="${product.getDescription()}"/></td>
                <td><c:out value="${product.getCategory().getId()}"/></td>
                <td>
                    <a href="/product?action=update&id=${product.id}">Edit</a>
                    <a href="/product?action=delete&id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>