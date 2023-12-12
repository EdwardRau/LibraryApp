<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="books">

    <div class="container text-center">
        <div class="row">
            <h1>Books</h1>
        </div>
        <c:forEach var="book" items="${books}">
            <div class="row">
                <div class="col-3">
                    ${book.title}
                </div>
                <div class="col-3">
                        ${book.author}
                </div>
                <div class="col-3">
                        ${book.genre}
                </div>
                <div class="col-3">
                        ${book.ownerName}
                </div>

            </div>
        </c:forEach>
    </div>
    <h5>${numberOfBookCopies}</h5>
</t:pageTemplate>