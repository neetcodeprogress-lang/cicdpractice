package org.example.service;

import java.util.List;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Business operations for persons. */
@Service
public class PersonService {

  private final PersonRepository personRepository;

  /** Injects the repository. */
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /** Saves a person. */
  @Transactional
  public Person create(Person person) {
    return personRepository.save(person);
  }

  /** Returns every person. */
  @Transactional(readOnly = true)
  public List<Person> findAll() {
    return personRepository.findAll();
  }
}
