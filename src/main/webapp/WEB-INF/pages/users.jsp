<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="users">

    <div class="container text-center">
    <div class="row">
        <h1>Users</h1>
    </div>
        <c:forEach var="user" items="${users}">
            <div class="row">
                <div class="col-6">
                        ${user.username}
                </div>
                <div class="col-6">
                        ${user.role}
                </div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>