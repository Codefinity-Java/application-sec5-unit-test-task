package com.codefinity.firstrestapibookssec4.controller;

import com.codefinity.firstrestapibookssec4.dto.BookRequestDTO;
import com.codefinity.firstrestapibookssec4.dto.BookResponseDTO;
import com.codefinity.firstrestapibookssec4.exception.ApiException;
import com.codefinity.firstrestapibookssec4.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BookRequestDTO bookRequestDTO;
    private BookResponseDTO bookResponseDTO;

    @BeforeEach
    void setUp() {
        bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setAuthor("authorTest");
        bookRequestDTO.setName("nameTest");
        bookRequestDTO.setPrice("priceTest");

        bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId("1");
        bookResponseDTO.setAuthor("authorTest");
        bookResponseDTO.setName("nameTest");
        bookResponseDTO.setPrice("priceTest");
    }

    //----findAllBooks----

    @Test
    void testFindAllBooks_whenBooksExists_shouldReturnBookList() throws Exception {
        when(bookService.findAllBooks()).thenReturn(List.of(bookResponseDTO));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(bookResponseDTO.getId()))
                .andExpect(jsonPath("$[0].name").value(bookResponseDTO.getName()))
                .andExpect(jsonPath("$[0].author").value(bookResponseDTO.getAuthor()))
                .andExpect(jsonPath("$[0].price").value(bookResponseDTO.getPrice()));;

        verify(bookService).findAllBooks();
    }

    @Test
    void testFindAllBooks_whenNoBooksExist_shouldReturnEmptyList() throws Exception {
        when(bookService.findAllBooks()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verify(bookService).findAllBooks();
    }

    //----createBook----

    @Test
    void testCreateBook_whenValidRequest_shouldReturnCreatedBook() throws Exception {
        when(bookService.createBook(any(BookRequestDTO.class))).thenReturn(bookResponseDTO);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bookRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookResponseDTO.getId()))
                .andExpect(jsonPath("$.name").value(bookResponseDTO.getName()))
                .andExpect(jsonPath("$.author").value(bookResponseDTO.getAuthor()))
                .andExpect(jsonPath("$.price").value(bookResponseDTO.getPrice()));

        verify(bookService, times(1)).createBook(any(BookRequestDTO.class));
    }

    //----updateBook----

    @Test
    void testUpdateBook_whenBookExists_shouldReturnUpdatedBook() throws Exception {
        String bookId = "1";

        when(bookService.updateBook(bookId, bookRequestDTO)).thenReturn(bookResponseDTO);

        mockMvc.perform(put("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookResponseDTO.getId()))
                .andExpect(jsonPath("$.name").value(bookResponseDTO.getName()))
                .andExpect(jsonPath("$.author").value(bookResponseDTO.getAuthor()))
                .andExpect(jsonPath("$.price").value(bookResponseDTO.getPrice()));

       verify(bookService).updateBook(bookId, bookRequestDTO);
    }


    @Test
    void testUpdateBook_whenBookNotFound_shouldReturnApiException() throws Exception {
        String bookId = "1";
        String errorMessage = "ID not found";

        when(bookService.updateBook(bookId, bookRequestDTO))
                .thenThrow(new ApiException(errorMessage, HttpStatus.NOT_FOUND));

        mockMvc.perform(put("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequestDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorMessage));

        verify(bookService).updateBook(bookId, bookRequestDTO);
    }

    //----deleteBook----

    //TODO: You need to implement 2 tests for the `deleteBook` method of the controller:
    // 1. When the Book entity is successfully deleted.
    // 2. When the entity with the given `ID` is not found, and handle the error.

    //----findByAuthor----

    //TODO: You need to implement 2 tests for the `findByAuthor` method of the controller:
    // 1.When the list of entities by the given author's name is not empty.
    // 2.When the list of entities by the given author's name is empty.

}
