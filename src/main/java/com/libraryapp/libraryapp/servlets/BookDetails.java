package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.ejb.BooksBean;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BookDetails", value = "/BookDetails")
public class BookDetails extends HttpServlet {

    @Inject
    BooksBean booksBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = null;
        String bookIdParam = request.getParameter("id");

        if (bookIdParam != null && !bookIdParam.isEmpty()) {
            id = Long.parseLong(bookIdParam);
        }

        BookDto book = booksBean.findBookById(id);

        if (book != null) {
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/pages/bookDetails.jsp").forward(request, response);
        } else {
            // Handle case where book is not found
            response.sendRedirect(request.getContextPath() + "/books");
        }
    }
}
