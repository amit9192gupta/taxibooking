package edu.jsp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="bookingform")
public class BookingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="name cannot be empty")
	@NotBlank(message="name can't be blank")
	@Size(min=3,max=30,message="invalid name length")
	@Pattern(regexp = "[A-Za-z]+$", message="name must contain only alpabate ")
	@Column(length = 30)
	private String name;
	
	@NotEmpty(message="email cannot be empty")
	@Email(message="email should be in proper format")
	@Size(min=3,max=50,message="invalid email length")
	@Column(length = 50)
	private String email;
	
	@NotEmpty(message="source cannot be empty")
	@NotBlank(message="source can't be blank")
	@Size(min=3,max=100,message="invalid source length")
	@Column(name="source" ,length = 100)
	private String from;
	
	@NotEmpty(message="destination can't be empty")
	@NotBlank(message="destination can't be blank")
	@Size(min=3,max=100,message="invalid destination length")
	@Column(name="destination",length = 100)
	private String to;
	 
	@NotNull(message="time cannot be empty")
	private LocalTime time;
	 
	@NotNull(message="message cannot be empty")
	private LocalDate date;
	
	@NotEmpty(message="please select the comfort")
	private String comfort;
	
	@Min(value=1,message="adult can be at least 1")
	@Max(value=4,message="adult can be at most 4 ")
	private int adult;
	
	@Max(value=3,message="children can be at most 3"	)
	private int children;
	
	@NotEmpty(message="message can't be empty")
	@NotBlank(message="message cannot be blank")
	@Size(min=3,max=300,message="invalid message length")
	@Column(length = 300)
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getComfort() {
		return comfort;
	}

	public void setComfort(String comfort) {
		this.comfort = comfort;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
	
	
}
