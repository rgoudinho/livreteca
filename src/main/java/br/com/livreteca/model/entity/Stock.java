package br.com.livreteca.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@GeneratedValue
	private long id;
	
	@Column (name = "id_livro")
	private int idLivro;
	
	private int amount;

	public Stock() {}
	
	public Stock(int idLivro, int amount) {
		this.idLivro = idLivro;
		this.amount = amount;
	}

	public int getAmount() { return amount; }

	public void setAmount(int amount) { this.amount = amount; }

	public long getId() { return id; }

	public int getIdLivro() { return idLivro; }
}
