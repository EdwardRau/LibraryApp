package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.ejb.BooksBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_BOOKS"}))
@WebServlet(name = "addBook", value = "/addBook")
public class AddBook extends HttpServlet {
    @Inject
    BooksBean booksBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<BookDto> books = booksBean.findAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/WEB-INF/pages/addBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        boolean isLoaned= Boolean.parseBoolean(request.getParameter("isLoaned"));

        booksBean.createBook(title,author,genre,isLoaned);
        response.sendRedirect(request.getContextPath() + "/books");
    }
}