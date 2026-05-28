package com.SurveyApp.userProfile.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Size;
import static java.lang.Math.toIntExact;

import java.util.HashMap;
import java.util.stream.Collectors;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	@Size(max = 20)
	private String name;
	@Column(nullable = false)
	@Size(max = 20)
	private String login;
	@Column(nullable = false)
	@Size(min = 5)
	private String password;

	@Column(nullable = false)
	private Boolean changedLogin;

	@OneToOne(cascade = CascadeType.PERSIST)
	@PrimaryKeyJoinColumn
	private Account userAccount;

	@Column(unique = true, nullable = false)
	@Size(min = 4)
	private String email;


	public User() {}
	
	public User(
			String Inname,
			String Inlogin,
			String Inpassword,
			String Inemail

	) {

		if (Inname == null)
			throw new IllegalArgumentException("Name shouldnt be null");

		if (Inlogin == null)
			throw new IllegalArgumentException("Login shouldnt be null");

		if (Inpassword == null)
			throw new IllegalArgumentException("Password shouldnt be null");

		if (Inemail == null)
			throw new IllegalArgumentException("Email shouldnt be null");

		if (Inname.isBlank())
			throw new IllegalArgumentException("Name shouldnt be empty ");

		if (Inlogin.isBlank())
			throw new IllegalArgumentException("Login shouldnt be empty");

		if (Inpassword.isBlank())
			throw new IllegalArgumentException("Password shouldnt be empty");

		if (Inemail.isBlank())
			throw new IllegalArgumentException("Email shouldnt be empty");


		if(!Inname.matches("^([A-Z][a-z]+[d'\\-\\s]?)([A-Z][a-z]+[d'\\-\\s]?)*$"))
			throw new IllegalArgumentException("User Name doesnt match the correct format");

		if(!Inpassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"))
			throw new IllegalArgumentException("Password for User SignUp DTO doesnt match the correct format");
		
		if(!Inemail.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+$"))
			throw new IllegalArgumentException("Email for User SignUp DTO est null");

		this.name = Inname;
		
		StringBuffer buff = new StringBuffer("");
		buff.append(Inlogin.toLowerCase());
		
		for(int i =0 ; i< name.length();i++){
			char symb = name.charAt(i);
			if( symb == ' '){
				buff = buff.replace(i, i+1, "_");
			}
		}

		
		this.login = buff.toString();
		this.password = Inpassword;
		this.email = Inemail;
		this.changedLogin = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "email= "+email+"changedLogin= "+changedLogin+"]";
	}



	public String toJsonString(){
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("name", this.name);
		map.put("login", this.login);
		map.put("email", this.email);
		map.put("changedLogin",this.changedLogin);

		String mapAsString = map.keySet().stream()
		.map(key -> key + ":" + map.get(key))
		.collect(Collectors.joining(", ", "{", "}"));

		return mapAsString;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + toIntExact(id);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getChangedLogin() {
		return changedLogin;
	}

	public void setChangedLogin(Boolean changedLogin) {
		this.changedLogin = changedLogin;
	}

}
