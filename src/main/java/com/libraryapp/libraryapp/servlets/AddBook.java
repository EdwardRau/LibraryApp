package com.libraryapp.libraryapp.servlets;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.ejb.BooksBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_BOOKS"}))
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");

        // Process image upload
        Part filePart = request.getPart("image");
        String fileName = booksBean.extractFileName(filePart);
        String imagePath = "/assets/images/books/" + fileName; // Relative path to the image

        // Save book information in the database
        booksBean.createBook(title, author, genre, description, imagePath);

        // Save the image in the designated folder
        String uploadPath = getServletContext().getRealPath("") + File.separator + "/assets/images/books";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, new File(uploadDir, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        response.sendRedirect(request.getContextPath() + "/books");
    }

}