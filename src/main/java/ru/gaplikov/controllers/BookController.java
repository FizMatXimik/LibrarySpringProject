package ru.gaplikov.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.services.BookService;
import ru.gaplikov.services.PersonService;
import ru.gaplikov.models.Book;
import ru.gaplikov.models.Person;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));

        Person owner = bookService.getBookOwner(id);
        if(owner != null) {
            model.addAttribute("currentPerson", owner);
        } else {
            model.addAttribute("people", personService.findAll());
        }
        return "books/book";
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookService.updateOwnerBook(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookService.updateOwnerBook(id, null);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String showNewFormBook(@ModelAttribute("book") Book book) {
        return "books/newForm";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/newForm";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateFormBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/updateForm";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/updateForm";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
