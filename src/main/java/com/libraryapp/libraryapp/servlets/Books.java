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
import java.util.List;

@WebServlet(name = "books", value = "/books")
public class Books extends HttpServlet {

    @Inject
    BooksBean booksBean;
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<BookDto> books = booksBean.findAllBooks();
        request.setAttribute("books", books);
        List<UserDto>users=usersBean.findAllUsers();
        users.sort((user1, user2) -> String.CASE_INSENSITIVE_ORDER.compare(user1.getUsername(), user2.getUsername()));
        request.setAttribute("users",users);
        request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if("changeOwner".equals(action)){
            Long bookId=Long.parseLong(request.getParameter("bookId"));
            Long newOwnerId=Long.parseLong(request.getParameter("newOwnerId"));
            booksBean.changeOwner(bookId,newOwnerId);
            response.sendRedirect(request.getContextPath()+"/books");
        }

    }

}