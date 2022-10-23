package ru.gaplikov.config.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gaplikov.models.Book;
import ru.gaplikov.models.Person;
import ru.gaplikov.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
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
}
