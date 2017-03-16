<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<div class="left-menu">

        <ul>
            <c:forEach var="g" items="${genres}">
                <li><a href="/searchCriterion/genre?id=${g.id}">${g.name}</a></li>
            </c:forEach>
        </ul>


</div>
