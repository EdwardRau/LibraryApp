<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="books">
    <div class="text-center">
        <h1>Books</h1>
    <div class="table-responsive">
    <table class="table table-dark table-hover aling-middle text-center">
        <thead>
        <tr>
            <th scope="col">First</th>
            <th scope="col">Author</th>
            <th scope="col">Genre</th>
            <th scope="col">Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr class="table-secondary">
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.genre}</td>
                <td>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/editBook" role="button">Change owner</a>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteBook" role="button">Delete</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/addBook" role="button">Add new book</a>
    </div>

</t:pageTemplate>