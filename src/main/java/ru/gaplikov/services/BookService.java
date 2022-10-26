package ru.gaplikov.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.models.Book;
import ru.gaplikov.models.Person;
import ru.gaplikov.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(Integer page, Integer books_per_page, Boolean sort_by_year) {
        if (page != null && books_per_page != null && sort_by_year != null) {
            return bookRepository.findAll(PageRequest.of(page, books_per_page,  Sort.by("year"))).getContent();
        } else if (page != null && books_per_page != null) {
            return bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
        } else if (sort_by_year != null) {
            return bookRepository.findAll(Sort.by("year"));
        }
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateOwnerBook(int id, Person person) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if(foundBook.isPresent()) {
            foundBook.get().setPerson(person);
            foundBook.get().setTookAt(new Date());
        }
    }

    public Person getBookOwner(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if(foundBook.isPresent()) {
            Hibernate.initialize(foundBook.get().getPerson());
            return foundBook.get().getPerson();
        }
        return null;
    }

    public Book findBookByNameLike(String name) {
        if (!Objects.equals(name, "")) {
            return bookRepository.findBookByNameLike("%" + name + "%");
        } else {
            return null;
        }
    }
}
