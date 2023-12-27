<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Loan Book">
    <div class="text-center">
        <h1>Loan Book</h1>
        <form method="post" action="${pageContext.request.contextPath}/loanBook">
            <c:if test="${not empty book}">
                <input type="hidden" name="action" value="loanBook">
                <input type="hidden" name="bookId" value="${book.id}">

                <label for="newOwnerId">Select Owner:</label>
                <select id="newOwnerId" name="newOwnerId" class="form-select" aria-label="Default select example">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}" <c:if test="${user.username eq book.ownerName}">selected</c:if>>${user.username}</option>
                    </c:forEach>
                </select>

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>

                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" required>

                <button type="submit" class="btn btn-primary">Loan Book</button>
            </c:if>
        </form>
    </div>
</t:pageTemplate>
