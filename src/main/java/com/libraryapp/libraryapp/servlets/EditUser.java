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

            List<UserDto> users = usersBean.findAllUsers();
            request.setAttribute("users", users);

            Long userId = Long.parseLong(request.getParameter("id"));
            UserDto user = usersBean.findById(userId);
            request.setAttribute("user", user);

            request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request, response);
        }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String userIdStr = request.getParameter("user_id");
        String newUsername = request.getParameter("new_username");
        String newEmail = request.getParameter("new_email");
        String newPassword = request.getParameter("new_password");
        String newRole = request.getParameter("new_role");
        String[] groupArray = request.getParameterValues("new_groups");
        Collection<String> newGroups = (groupArray != null) ? Arrays.asList(groupArray) : Collections.emptyList();

        if (userIdStr != null && !userIdStr.isEmpty()) {
            Long userId = Long.parseLong(userIdStr);
            usersBean.editUser(userId, newUsername, newEmail, newPassword, newRole, newGroups);
        }

        response.sendRedirect(request.getContextPath() + "/Users");
    }
}


