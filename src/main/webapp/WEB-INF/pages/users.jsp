<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="users">
    <div class="text-center">
        <h1>Users</h1>
        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
            <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary
btn-lg">Add User</a>
        </c:if>
    </div>
    <div class="table-responsive">
        <table class="table table-dark table-hover aling-middle text-center">
            <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Role</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr class="table-secondary">
                    <td>${user.username}</td>
                    <td>${user.role}</td>
                    <td>
                        <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                            <!-- Add the Edit button with a link to the EditUser servlet -->
                            <a href="${pageContext.request.contextPath}/EditUser?id=${user.id}" class="btn btn-warning">Edit</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:pageTemplate>