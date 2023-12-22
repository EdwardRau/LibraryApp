package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.common.UserDto;
import com.libraryapp.libraryapp.ejb.BooksBean;
import com.libraryapp.libraryapp.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "EditUser",value = "/EditUser")
public class EditUser extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Long userId=Long.parseLong(request.getParameter("id"));
        UserDto user=usersBean.findById(userId);
        request.setAttribute("user",user);
        request.setAttribute("userGroups",new String[]{"READ_BOOKS","WRITE_BOOKS","READ_USERS","WRITE_USERS"});
        request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request,response);
        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String role = request.getParameter("role");
        String[] userGroups = request.getParameterValues("user_groups");
        Long userId=Long.parseLong(request.getParameter("user_id"));
        usersBean.updateUser(userId,username, email, password, role, Arrays.asList(userGroups));
        response.sendRedirect(request.getContextPath() + "/users");
    }
}