package com.libraryapp.libraryapp.ejb;

import com.libraryapp.libraryapp.common.BookDto;
import com.libraryapp.libraryapp.entities.Book;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class BooksBean {
    private static final Logger LOG = Logger.getLogger(BooksBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<BookDto> findAllBooks() {
        LOG.info("findAllBooks");
        try{
            TypedQuery<Book> typedQuery = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            List<Book> books = typedQuery.getResultList();
            return copyBooksToDto(books);
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<BookDto> copyBooksToDto(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();

        for (var book : books
        ) {
            bookDtos.add(new BookDto(book.getId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getOwner().getUsername()));
        }
        return bookDtos;
    }
}
