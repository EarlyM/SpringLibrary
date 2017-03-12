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

    <div class="content">
        <%@include file="templates/menu.jsp" %>

        <div class="book_list">
            <div class="right-content">
                <div class="add-div"><button class="add-button"><a class="" href="/admin">Добавить</a></button></div>
                <c:forEach items="${pages.books}" var="b">
                    <div class="item">
                        <img src="/content/img=${b.id}">
                        <div class="info">
                            <div class="book_name">
                                <p><a class="title">${b.name}</a></p>
                            </div>

                            <span>${b.author.fio}</span>
                            <p><strong>ISBN:</strong> ${b.isbn}</p>
                            <p><strong>Издательство:</strong> ${b.publisher.name}</p>
                            <p><strong>Количество страниц:</strong> ${b.pageCount}</p>
                            <p><strong>Год издания:</strong> ${b.publishYear}</p>
                        </div>
                        <button class="mr"><a class="read" href="/content/read=${b.id}" target="_blank">Читать</a></button>
                        <button class="mr"><a class="download" href="/content/download/id=${b.id}&name=${b.name}">Скачать</a></button>
                        <button class="mr"><a class="edit" href="/admin?id=${b.id}">Изменить</a></button>
                        <button class="delete-button"><a class="delete" href="/delete?id=${b.id}">Удалить</a></button>
                    </div>
                </c:forEach>
            </div>

            <div class="pagination">
                <c:if test="${pages.pageCount > 1}">
                    <spring:eval expression="@util.getPath(pageContext.request)" var="url"/>
                    <ul>
                        <c:forEach begin="1" end="${pages.pageCount}" step="1" varStatus="p">
                            <li><a id="a" href="${url}page=${p.index}">${p.index}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>

    </div>

    <%@include file="templates/footer.jsp" %>
</div>
</body>
</html>
