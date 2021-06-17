package com.demo.ecom.dto;

public class ProductDTO {
	
    private Long id;
    
    private String name;
	
    private String categoryId;
	
    private String price;
    
    private ProductDTO(ProductBuilder productBuilder) {
    	this.id = productBuilder.id;
    	this.categoryId = productBuilder.categoryId;
    	this.name = productBuilder.name;
    	this.price = productBuilder.price;
    }
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getPrice() {
		return price;
	}

	public static class ProductBuilder {
		
		private Long id;
	    
	    private String name;
		
	    private String categoryId;
		
	    private String price;
 
        public ProductBuilder() {
        }
        
        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public ProductBuilder price(String price) {
            this.price = price;
            return this;
        }
        
        public ProductBuilder category(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        
        public ProductDTO build() {
        	ProductDTO product =  new ProductDTO(this);
            return product;
        }
    }

}
