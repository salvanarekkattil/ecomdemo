package com.demo.ecom.dto;

import java.io.Serializable;
import java.util.List;

public class Checkout implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7367006867627228276L;
	
	private List<ProductDTO> products;
	
	private Double netValue;

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

}
