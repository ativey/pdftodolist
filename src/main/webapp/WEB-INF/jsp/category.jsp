<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<h1>Categories</h1>

<p><a href="addCategory">Add Category</a></p>
<p><a href="renameCategory">Rename Category</a></p>

<p>List of categories</p>

<table style="border: 1px solid;">
    <!-- iterate over the collection using forEach loop -->
    <c:forEach var="category" items="${categories}">
        <tr>
            <!-- create cells of row -->
            <td>${category.name}</td>
            <!-- <td>${user.location}</td> -->
        </tr>
    </c:forEach>
</table>

</html>