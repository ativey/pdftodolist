<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<h1>ToDoPDF</h1>

<p>
    <a href="category">Category</a>
    <a href="generatePdf">PDF</a>
    <a href="addTask">Add Task</a>
    <a href="editTask">Edit Task</a>
    <a href="importTasks">Import Tasks</a>
</p>

<p>List of tasks</p>

    <c:forEach var="category" items="${categories}">
            <h1>${category.name}</h1>
    </c:forEach>


<table style="border: 1px solid;">
    <!-- iterate over the collection using forEach loop -->
    <c:forEach var="task" items="${tasks}">
        <tr>
            <!-- create cells of row -->
            <td>${task.name}</td>
            <!-- <td>${user.location}</td> -->
        </tr>
    </c:forEach>
</table>


</html>