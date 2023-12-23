package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "DeleteUser",value = "/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        usersBean.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/users");
    }
}
