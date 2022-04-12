<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Manager</title>
</head>
<body>
<center>
    <h1>Product Management</h1>

</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Update Product
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
            </c:if>
            <tr>
                <th>Product Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.name}'/>"
                    />
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="number" name="price" size="45"
                           value="<c:out value='${product.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Color:</th>
                <td>
                    <input type="text" name="color" size="15"
                           value="<c:out value='${product.color}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Description</th>
                <td>
                    <input type="text" name="description" size="15"
                           value="<c:out value='${product.description}'/>"
                    />
                </td>
            </tr>
            <tr>
                <th>Category</th>
                <td>
                    <select name="categoryId" >
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>