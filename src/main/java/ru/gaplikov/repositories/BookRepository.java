package ru.gaplikov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaplikov.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
