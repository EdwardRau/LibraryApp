package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.ejb.BooksBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "deleteBook", value = "/deleteBook")
public class deleteBook extends HttpServlet {
    @Inject
    BooksBean booksBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        booksBean.deleteBook(id);
        response.sendRedirect(request.getContextPath() + "/books");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}