<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="logo">
    <img src="/resources/img/logo.png"/>
    <div class="container" style="width: 300px;">
        <form action="/j_spring_security_check" method="post">
            <input type="text" name="username">
            <input type="password" name="password">
            <button type="submit">Войти</button>
        </form>
    </div>
    <div class="clear"></div>
</div>

<div class="clear"></div>