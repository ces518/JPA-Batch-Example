package me.june.jpabatchexample;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book implements Serializable {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(
		name = "SequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "hibernate_sequence"),
			@Parameter(name = "optimizer", value = "pooled"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "5000")
		}
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "SequenceGenerator"
	)
	private Long id;

	private String title;

	public Book(String title) {
		this.title = title;
	}
}
