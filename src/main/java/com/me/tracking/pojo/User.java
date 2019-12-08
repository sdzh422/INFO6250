package com.me.tracking.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@Size(min=2,max=10,message = "Length of username must be in 4-10 characters.")
	private String username;
	
	@Column
	@NotEmpty(message = "Password cannot be empty.")
	private String password;
	
	@Column
	@NotEmpty(message = "Phone number cannot be empty.")
	@Pattern(regexp="\\d{10,11}",message="invalid phone number")
	private String phone;
	
	@Column
	@NotEmpty(message = "Address cannot be empty.")
	private String address;
	
	@Column
	private String type;
	
	@Column
	@NotEmpty(message = "Email cannot be empty.")
	@Email(message="Invalid Email.")
	private String email;
	
	@Column
	private String date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
