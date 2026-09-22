package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/** Covers the accessors, which Jackson and Hibernate both rely on. */
class PersonTest {

  @Test
  void startsEmpty() {
    Person person = new Person();
    assertNull(person.getId());
    assertNull(person.getFirstName());
    assertNull(person.getLastName());
    assertNull(person.getEmail());
  }

  @Test
  void readsBackEverySetter() {
    Person person = new Person();
    person.setId(7L);
    person.setFirstName("Ada");
    person.setLastName("Lovelace");
    person.setEmail("ada@example.com");

    assertEquals(7L, person.getId());
    assertEquals("Ada", person.getFirstName());
    assertEquals("Lovelace", person.getLastName());
    assertEquals("ada@example.com", person.getEmail());
  }
}
