<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Edit User">
    <h1>Edit User</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditUser">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="new_username">Username</label>
                <input type="text" class="form-control" id="new_username" name="new_username" placeholder="" value="${user.username}" required>
                <div class="invalid-feedback">
                    Username is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="new_email">Email</label>
                <input type="email" class="form-control" id="new_email" name="new_email" placeholder="" value="${user.email}" required>
                <div class="invalid-feedback">
                    Email is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="new_password">Password</label>
                <input type="password" class="form-control" id="new_password" name="new_password" placeholder="" required>
                <div class="invalid-feedback">
                    Password is Required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="new_role" class="form-label">Role</label>
                <select class="form-select" id="new_role" name="new_role" required>
                    <option value="">Choose...</option>
                    <!-- Adjust the values based on your roles -->
                    <option value="ADMIN" ${user.role eq 'ADMIN' ? 'selected' : ''}>Admin</option>
                    <option value="USER" ${user.role eq 'USER' ? 'selected' : ''}>User</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid role
                </div>
            </div>
        </div>
        <button class="w-100 btn btn-primary btn-lg" type="submit">Submit</button>
        <hr class="mb-4">
        <input type="hidden" name="user_id" value="${user.id}" />
    </form>
</t:pageTemplate>
