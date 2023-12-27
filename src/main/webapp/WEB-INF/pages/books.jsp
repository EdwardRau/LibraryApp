<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="books">
    <div class="text-center">
        <h1>Books</h1>
        <div class="table-responsive">
            <table class="table table-dark table-hover align-middle text-center">
                <thead>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Author</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Availability</th>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                        <th scope="col">Owner</th>
                    </c:if>
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
                            <c:choose>
                                <c:when test="${book.loaned}">
                                    <span class="text-danger">Unavailable</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-success">Available</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                            <td>${book.ownerName}</td>
                        </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/loanBook?id=${book.id}" role="button">Edit</a>
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteBook?id=${book.id}" role="button">Delete</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-warning" href="${pageContext.request.contextPath}/loan?id=${book.id}" role="button">Loan</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:pageTemplate>
