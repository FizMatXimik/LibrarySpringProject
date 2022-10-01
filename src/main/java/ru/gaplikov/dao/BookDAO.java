package ru.gaplikov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gaplikov.models.Book;
import ru.gaplikov.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT book.* FROM book inner join person on book.person_id=person.id WHERE book.person_id = ?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
