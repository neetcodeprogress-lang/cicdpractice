package org.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.example.model.Person;
import org.example.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/** Web layer only. The service is mocked, so no database is needed. */
@WebMvcTest(PersonController.class)
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private PersonService personService;

  @Test
  void postReturns201AndTheSavedPerson() throws Exception {
    Person saved = new Person();
    saved.setId(1L);
    saved.setFirstName("Ada");
    saved.setLastName("Lovelace");
    saved.setEmail("ada@example.com");
    given(personService.create(any(Person.class))).willReturn(saved);

    Person request = new Person();
    request.setFirstName("Ada");

    mockMvc.perform(post("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstName").value("Ada"))
        .andExpect(jsonPath("$.email").value("ada@example.com"));
  }

  @Test
  void getReturnsEveryPerson() throws Exception {
    Person person = new Person();
    person.setId(2L);
    person.setFirstName("Grace");
    given(personService.findAll()).willReturn(List.of(person));

    mockMvc.perform(get("/api/persons"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(2))
        .andExpect(jsonPath("$[0].firstName").value("Grace"));
  }
}
