package com.demo.ecom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product")
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@Column(name="prod_name")
    private String name;
	
	@Column(name="category_id")
    private String categoryId;
	
	@Column(name="price")
    private String price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
