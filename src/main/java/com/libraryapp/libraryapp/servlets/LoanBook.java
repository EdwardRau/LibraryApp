package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.common.LoanDto;
import com.libraryapp.libraryapp.common.UserDto;
import com.libraryapp.libraryapp.ejb.BooksBean;
import com.libraryapp.libraryapp.ejb.LoanBean;
import com.libraryapp.libraryapp.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "LoanBook", value = "/loanBook")
public class LoanBook extends HttpServlet {
    @Inject
    UsersBean usersBean;
    @Inject
    BooksBean booksBean;
    @Inject
    LoanBean loanBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.parseLong(request.getParameter("id"));
        BookDto book = booksBean.findBookById(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/WEB-INF/pages/loanBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("loanBook".equals(action)) {
            Long bookId = Long.parseLong(request.getParameter("bookId"));
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
            BookDto book = booksBean.findBookById(bookId);
            if (book != null && !book.isLoaned()) {
                loanBean.createLoan(bookId, startDate, endDate);

                response.sendRedirect(request.getContextPath() + "/books");
            } else {
                response.sendRedirect(request.getContextPath() + "/loanBook?id=" + bookId);
            }
        }
    }
}
