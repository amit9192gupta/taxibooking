package edu.jsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="contactform")
@Entity
public class ContactForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Name cannot be empty ")
	@Size(min = 2, max = 30, message = "Invalid Name size")
	private String name;

	@Email(message="please enter a valid email")
	@NotEmpty(message="email cannot be empty")
	@Column(length=30)
	private String email;
	
    @NotNull(message="phone no can't be empty ")
	@Min(value=1000000000L,message="Phone no must be at least 10 digits")
	@Max(value=9999999999L,message="Phone no must be of 10 digit")
    @Column(length=10)
	private Long phone;

	@NotEmpty(message="cannot be empty")
	@Size(min=3,max=250,message="Invalid message size")
	@Column(length=500)
	private String message;
	
	

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
