package ru.gaplikov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gaplikov.controllers.PersonController;
import ru.gaplikov.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public Optional<Person> getPerson(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio = ?", new BeanPropertyRowMapper<>(Person.class), fio).stream().findAny();
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, birth_year) VALUES(?, ?)", person.getFio(), person.getBirth_year());
    }

    public void updatePerson(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET fio=?, birth_year=? WHERE id=?", updatePerson.getFio(), updatePerson.getBirth_year(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
