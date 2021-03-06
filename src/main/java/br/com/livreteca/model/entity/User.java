package br.com.livreteca.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	private String login;
	private String pwd;
	private String email;
	
	public User() {
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public User(String login,String pwd, String email) {
		this.login = login;
		this.pwd = pwd;
		this.email = email;
	}
	
	public User(int id, String login,String pwd, String email) {
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}