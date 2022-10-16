package ru.gaplikov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.dao.BookDAO;
import ru.gaplikov.dao.PersonDAO;
import ru.gaplikov.models.Book;
import ru.gaplikov.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("people", personDAO.getAllPeople());
        model.addAttribute("currentPerson", personDAO.getPersonByBookId(id));
        return "books/book";
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.updateOwnerBook(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.updateOwnerBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String showNewFormBook(@ModelAttribute("book") Book book) {
        return "books/newForm";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book,
                                  BindingResult bindingResult) {
        //bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/newForm";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateFormBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/updateForm";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/updateForm";
        }

        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
