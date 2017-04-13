<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">
</head>
<body>
<div id="wrap">
    <%@include file="templates/logo.jsp" %>
    <%@include file="templates/search.jsp"%>
    <%@include file="templates/letters.jsp" %>

    <div class="content">
        <%@include file="templates/menu.jsp" %>


        <div class="form">
            <form action="/j_spring_security_check" method="post" class="login">
                <c:if test="${requestScope.get('error') ne null}">
                    <p>${requestScope.get('error')}</p>
                </c:if>
                <input type="text" name="username" placeholder="Username">
                <input type="password" name="password" placeholder="Password">
                <div class="helps">
                    <a href="#">Регистрация</a><br/>
                    <a href="#">Забили пароль?</a>
                </div>
                <button type="submit">Войти</button>
            </form>
        </div>

    </div>

    <%@include file="templates/footer.jsp" %>
</div>
</body>
</html>
