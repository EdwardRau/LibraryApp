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
@ServletSecurity(httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"WRITE_BOOKS"})})
@WebServlet(name = "books", value = "/books")
public class Books extends HttpServlet {

    @Inject
    BooksBean booksBean;
    @Inject
    UsersBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        Long id = null;
        String bookIdParam = request.getParameter("id");

        if (bookIdParam != null && !bookIdParam.isEmpty()) {
            id = Long.parseLong(bookIdParam);
        }

        List<BookDto> books = booksBean.findAllBooks();
        request.setAttribute("books", books);
        request.setAttribute("id", id);
        request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}