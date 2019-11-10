package br.com.livreteca.dto;

public class BookDTO {
	private long id;
	private String name;
	private String writer;
	private int amount;

	public BookDTO() {
	}

	public BookDTO(String name, String writer, int amount) {
		this.name = name;
		this.writer = writer;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
