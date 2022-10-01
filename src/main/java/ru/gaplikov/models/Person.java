package ru.gaplikov.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО должно быть заполнено")
    private String fio;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int birth_year;

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
}
