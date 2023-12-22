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
                    <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                    <th scope="col">Owner</th>
                    <th scope="col">Options</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${books}">
                    <tr class="table-secondary">
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.genre}</td>
                        <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                        <td>${book.ownerName}</td>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
                        <td>
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#changeOwnerModal${book.id}">
                                Change owner
                            </button>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteBook?id=${book.id}" role="button">Delete</a>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <c:if test="${pageContext.request.isUserInRole('WRITE_BOOKS')}">
    <c:forEach var="book" items="${books}">
        <div class="modal fade" id="changeOwnerModal${book.id}" tabindex="-1" aria-labelledby="changeOwnerModalLabel${book.id}" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="changeOwnerModalLabel${book.id}">Change Owner</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Display a form or list of owners here -->
                        <form method="post" action="${pageContext.request.contextPath}/books">
                            <input type="hidden" name="action" value="changeOwner">
                            <input type="hidden" name="bookId" value="${book.id}">

                            <label for="newOwnerId">Select Owner:</label>
                            <select id="newOwnerId" name="newOwnerId" class="form-select" aria-label="Default select example">
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.id}">${user.username}</option>
                                </c:forEach>
                            </select>

                            <button type="submit" class="btn btn-primary">Change Owner</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </c:if>
</t:pageTemplate>
