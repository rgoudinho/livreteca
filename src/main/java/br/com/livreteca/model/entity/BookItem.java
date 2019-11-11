package br.com.livreteca.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_items")
public class BookItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Book book;
	
	private boolean borrowed;

	public BookItem() {
	}
	
	public BookItem(Book book, boolean borrowed) {
		super();
		this.book = book;
		this.borrowed = borrowed;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getId() {
		return id;
	}

	public boolean getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
}
