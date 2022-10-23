package ru.gaplikov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaplikov.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
