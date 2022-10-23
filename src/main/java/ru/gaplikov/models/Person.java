package ru.gaplikov.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "ФИО должно быть заполнено")
    @Column(name = "fio")
    private String fio;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Column(name = "birth_year")
    private int birth_year;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {}

    public Person(int id, String fio, int birth_year) {
        this.id = id;
        this.fio = fio;
        this.birth_year = birth_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", birth_year=" + birth_year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && birth_year == person.birth_year && fio.equals(person.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, birth_year);
    }
}
