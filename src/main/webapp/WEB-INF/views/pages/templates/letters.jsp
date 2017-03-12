<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<div class="nav">
    <ul>
        <c:forEach items="${letters}" var="letter">
            <li><a href="letter?letter=${letter}">${letter}</a></li>
        </c:forEach>
    </ul>
</div>
