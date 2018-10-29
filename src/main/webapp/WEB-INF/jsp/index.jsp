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
<font size="8">
<ul>
<c:forEach var="pair" items="${list}">
    <li style="color:${pair.first.hexString};">
        <c:if test="${pair.second.important}"><b></c:if>
            <c:if test="${pair.second.complete}"><s></c:if>
            ${pair.second.name}
                <c:if test="${pair.second.complete}"></s></c:if>
            <c:if test="${pair.second.important}"></b></c:if>
    </li>
</c:forEach>
</ul>
</font>


</html>