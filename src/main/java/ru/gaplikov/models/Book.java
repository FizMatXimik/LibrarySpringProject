package ru.gaplikov.models;

public class Book {
    private int id;

    private String name;

    private String author;

    private int year;

    /*private int person_id;*/

    public Book() {}

    public Book(int id, String name, String author, int year/*, int person_id*/) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        /*this.person_id = person_id;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }*/

}
