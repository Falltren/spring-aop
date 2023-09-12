package com.example.springaop;

import com.example.springaop.entity.Book;
import com.example.springaop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringAopApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("Война и мир", "Л.Н. Толстой");
		Book book2 = new Book("Капитанская дочка", "А.С. Пушкин");
		bookRepository.save(book1);
		bookRepository.save(book2);

	}
}
