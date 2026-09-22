package org.example.repository;

import org.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/** Data access for persons. */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
