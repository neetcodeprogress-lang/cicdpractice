package org.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Plain unit test. No Spring context, so it runs in milliseconds. */
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  void createDelegatesToTheRepository() {
    Person incoming = new Person();
    Person saved = new Person();
    saved.setId(1L);
    given(personRepository.save(incoming)).willReturn(saved);

    assertSame(saved, personService.create(incoming));
    verify(personRepository).save(incoming);
  }

  @Test
  void findAllReturnsWhatTheRepositoryHolds() {
    Person person = new Person();
    given(personRepository.findAll()).willReturn(List.of(person));

    List<Person> found = personService.findAll();

    assertEquals(List.of(person), found);
  }
}
