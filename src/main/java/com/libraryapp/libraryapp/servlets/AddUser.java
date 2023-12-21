package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.HelloServlet;
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
import java.util.Arrays;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "AddUser",value = "/AddUser")
public class AddUser extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setAttribute("userGroups",new String[]{"READ_BOOKS","WRITE_BOOKS","READ_USERS","WRITE_USERS"});
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws  ServletException,IOException{
        String username=request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role=request.getParameter("role");
        String[] userGroups=request.getParameterValues("user_group");
        if (userGroups == null) {
            userGroups = new String[0];
        }
        usersBean.createUser(username,email,password,role, Arrays.asList(userGroups));
        response.sendRedirect(request.getContextPath()+"/users");
    }
}
