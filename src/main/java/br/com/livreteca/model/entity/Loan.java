package br.com.livreteca.model.entity;

import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name = "loans")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User id_user;

	@ManyToOne
	private Book id_book;

	@Column(name = "data_de_emprestimo")
	private Date dataDeEmprestimo;

	@Column(name = "data_de_entrega")
	private Date dataDeEntrega;

	public Loan() {
	}

	public Loan(User user, Book book, Date dataDeEmprestimo, Date dataDeEntrega) {
		super();
		this.id_user = user;
		this.id_book = book;
		this.dataDeEmprestimo = dataDeEmprestimo;
		this.dataDeEntrega = dataDeEntrega;
	}

	public User getId_User() {
		return id_user;
	}

	public void setId_User(User user) {
		this.id_user = user;
	}

	public Book getId_Book() {
		return id_book;
	}

	public void setid_Book(Book book) {
		this.id_book = book;
	}

	public Date getDataDeEmprestimo() {
		return dataDeEmprestimo;
	}

	public void setDataDeEmprestimo(Date dataDeEmprestimo) {
		this.dataDeEmprestimo = dataDeEmprestimo;
	}

	public Date getDataDeEntrega() {
		return dataDeEntrega;
	}

	public void setDataDeEntrega(Date dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}

	public Long getId() {
		return id;
	}
}
