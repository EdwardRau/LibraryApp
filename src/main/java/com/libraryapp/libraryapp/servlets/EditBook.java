package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.common.UserDto;
import com.libraryapp.libraryapp.ejb.BooksBean;
import com.libraryapp.libraryapp.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_BOOKS"}))
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(name = "EditBook", value = "/EditBook")
public class EditBook extends HttpServlet{
    @Inject
    UsersBean usersBean;
    @Inject
    BooksBean BooksBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        List<UserDto> users=usersBean.findAllUsers();
        request.setAttribute("users",users);
        Long bookId=Long.parseLong(request.getParameter("id"));
        BookDto book=BooksBean.findBookById(bookId);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/WEB-INF/pages/editBook.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String title=request.getParameter("title");
        String author=request.getParameter("author");
        String genre=request.getParameter("genre");
        String description=request.getParameter("description");
        Part filePart = request.getPart("image");
        String fileName = BooksBean.extractFileName(filePart);
        String imagePath = "/assets/images/books/" + fileName;
        Long bookId=Long.parseLong(request.getParameter("book_id"));
        BooksBean.updateBook(bookId,title,author,genre,description,imagePath);

        String uploadPath = getServletContext().getRealPath("") + File.separator + "/assets/images/books";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, new File(uploadDir, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        response.sendRedirect(request.getContextPath()+"/books");
    }
}