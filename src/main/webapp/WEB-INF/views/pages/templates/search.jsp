<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="search">


    <form method="get"  action="/searchCriterion/search" class="search_form" id="search_form">

        <input type="text"  id="search" name="search" placeholder="Поиск" class="search-text"/>

        <input type="submit" id="btnSearch" value="Поиск" class="button"/>


        <div class="dropdown">
            <select id="select" name="select">
                <option value="book">Книга</option>
                <option value="author">Автор</option>
            </select>
        </div>

    </form>

    <div class="clear"></div>

</div>
