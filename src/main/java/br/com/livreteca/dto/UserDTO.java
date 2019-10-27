package br.com.livreteca.dto;

public class UserDTO {

	protected int id;
	private String login;
	private String pwd;
	private String email;
	
	public UserDTO() {
		
	}
	
	public UserDTO(int id) {
		this.id = id;
	}
	
	public UserDTO(String login, String pwd, String email) {
		this.login = login;
		this.pwd = pwd;
		this.email = email;
	}
	
	public UserDTO(int id, String login, String pwd, String email) {
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
