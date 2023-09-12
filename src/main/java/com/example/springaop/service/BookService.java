package com.example.springaop.service;

import com.example.springaop.entity.Book;
import com.example.springaop.repository.BookRepository;
import com.example.springaop.util.CustomResponse;
import com.example.springaop.util.CustomStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;


    public CustomResponse<Book> getAll() {
        List<Book> books;
        try {
            log.info("Попытка получить все книги");
            books = bookRepository.findAll();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Все книги получены");
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> getBookByTitle(String title) {
        Book book;
        try {
            log.info("Попытка получить книгу с названием {}", title);
            book = bookRepository.findByTitle(title).orElseThrow();
        }catch (NoSuchElementException e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {} получена", title);
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> addBook(Book book) {
        Book newBook;
        try {
            log.info("Попытка добавить книгу с названием {}", book.getTitle());
            newBook =bookRepository.save(book);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {} добавлена", newBook.getTitle());
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }
}
