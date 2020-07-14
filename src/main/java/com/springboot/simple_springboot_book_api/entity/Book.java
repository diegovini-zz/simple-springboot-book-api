package com.springboot.simple_springboot_book_api.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BOOK")
public class Book {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="BOOK_ID")
	private long id;
	
	@Column(name="NAME")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(name="PRICE")
	@NotNull(message = "Price is mandatory")
	private BigDecimal price;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
