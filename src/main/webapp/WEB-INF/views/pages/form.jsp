<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Library</title>
    <link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">
</head>
<body>
<div id="wrap">
    <%@include file="templates/logo.jsp" %>
    <%@include file="templates/search.jsp"%>
    <%@include file="templates/letters.jsp" %>

    <spring:url value="/admin/add" var="add"/>
    <spring:url value="/admin/edit" var="edit"/>
    <div class="content">
        <%@include file="templates/menu.jsp" %>

        <div class="form">
        <sf:form modelAttribute="bookForm" method="post" enctype="multipart/form-data" action="${bookForm.id == null ? add : edit}">

            <div class="image-upload">
                <img src="/content/img=${bookForm.id}">
                <sf:input type="file" path="image"/>
            </div>
            <div class="edit">
                <sf:input path="id" type="hidden"/>
                <sf:errors cssClass="errorCss" path="name"/>
                <p><strong>Название:</strong> <sf:input cssClass="text" path="name"/></p>
                <sf:errors cssClass="errorCss" path="pageCount"/>
                <p><strong>Страниц:</strong>  <sf:input cssClass="text" path="pageCount"/></p>
                <sf:errors cssClass="errorCss" path="isbn"/>
                <p><strong>ISBN:</strong>  <sf:input cssClass="text" path="isbn"/></p>
                <sf:errors cssClass="errorCss" path="genre"/>
                <p><strong>Жанр:</strong>  <sf:input cssClass="text" path="genre"/></p>
                <sf:errors cssClass="errorCss" path="author"/>
                <p><strong>Автор:</strong>  <sf:input cssClass="text" path="author"/></p>
                <sf:errors cssClass="errorCss" path="publishYear"/>
                <p><strong>Год:</strong>  <sf:input cssClass="text" path="publishYear"/></p>
                <sf:errors cssClass="errorCss" path="publisher"/>
                <p><strong>Издатель:</strong>  <sf:input cssClass="text" path="publisher"/></p>
                <p><strong>Книга:</strong>  <sf:input type="file" cssClass="text" path="content"/></p>
                <input type="submit" value="Сохранить">
            </div>
        </sf:form>
        </div>

    </div>

    <%@include file="templates/footer.jsp" %>
</div>
</body>
</html>
