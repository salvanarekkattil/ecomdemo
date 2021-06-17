package com.demo.ecom.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.dto.Checkout;
import com.demo.ecom.dto.ProductDTO;
import com.demo.ecom.service.EcomService;

@RestController
@RequestMapping("/ecom")
public class EcomController {
	
	@Autowired EcomService service;
	
	@GetMapping("/cart/add/{id}")
    public ProductDTO addToCart(@PathVariable(required = true) long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = authentication.getName();
		ProductDTO product = service.getProductById(id);
		service.addToCart(product, principalName);
        return service.getProductById(id);
    }
	
	@GetMapping("/cart")
    public List<ProductDTO> getProducts() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = authentication.getName();
        return service.getProducts(principalName);
    }
	
	@GetMapping("/cart/checkout")
    public Checkout checkout() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = authentication.getName();
        return service.doCheckout(principalName);
    }

}
