package me.june.jpabatchexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class BookRepositoryTest {

	@Autowired
	BookRepository repository;

	@Test
	void test() {
		Book savedBook = repository.save(new Book("안녕"));
		System.out.println("savedBook.getId() = " + savedBook.getId());
	}

	@Test
	void save_all() {
		List<Book> books = IntStream.range(0, 100)
			.mapToObj(i -> new Book("title" + i))
			.collect(Collectors.toList());
		repository.saveAll(books);
	}
}