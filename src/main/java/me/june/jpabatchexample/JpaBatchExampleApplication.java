package me.june.jpabatchexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class JpaBatchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaBatchExampleApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {

			@Autowired
			BookRepository repository;

			@Override
			@Transactional
			public void run(ApplicationArguments args) throws Exception {
				StopWatch stopWatch = new StopWatch();
				stopWatch.start();
				List<Book> books = IntStream.range(0, 10000)
					.mapToObj(i -> new Book("title" + i))
					.collect(Collectors.toList());

				repository.saveAll(books);
				stopWatch.stop();
				System.out.println(stopWatch.prettyPrint());
			}
		};
	}
}
