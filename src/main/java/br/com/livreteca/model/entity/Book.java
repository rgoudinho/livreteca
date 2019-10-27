package br.com.livreteca.model.entity;

import javax.persistence.*;

@Entity
@Table (name = "books")
public class Book {
	
	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String writer;
	private boolean borrowed;
	
	public Book() {}
	
	public Book(String name, String writer) {
		this.name = name;
		this.writer = writer;
		borrowed = false;
	}

	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getWriter() { return writer; }
	
	public void setWriter(String writer) { this.writer = writer; }
	
	public boolean getAmount() { return borrowed; }
	
	public void setAmount(boolean borrowed) {	this.borrowed = borrowed; }
}
