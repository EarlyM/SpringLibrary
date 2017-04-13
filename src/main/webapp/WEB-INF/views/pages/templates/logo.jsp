<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="logo">
    <img src="/resources/img/logo.png"/>
</div>
<div class="loginForm">
    <sec:authorize access="anonymous">
        <div class="help">
            <a href="#">Регистрация</a>
            <a href="#">Забили пароль?</a>
        </div>
        <form action="/j_spring_security_check" method="post">
            <input type="text" name="username" placeholder="Username">
            <input type="password" name="password" placeholder="Password">
            <button type="submit">Войти</button>
        </form>
    </sec:authorize>
    <c:if test="${pageContext.request.userPrincipal ne null}">
        <span><c:out value="${pageContext.request.userPrincipal.name}"/> <a href="/logout" class="a">Выйти</a></span>
    </c:if>

</div>

<div class="clear"></div>