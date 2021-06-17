package com.demo.ecom.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.demo.ecom.cache.CachingService;
import com.demo.ecom.dto.Checkout;
import com.demo.ecom.dto.ProductDTO;
import com.demo.ecom.dto.UserDTO;
import com.demo.ecom.entity.UserEntity;
import com.demo.ecom.repository.ProductRepository;
import com.demo.ecom.repository.UserRepository;

@Component
public class EcomService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CachingService cacheService;
	
	@Value("${cache.name}")
	private String cacheName;
	
	private static final String GROCERY_CATEGORY = "3";
	
	
	public ProductDTO getProductById(Long id) {
		AtomicReference<ProductDTO> product = new AtomicReference<>(null);
		productRepo.findById(id).ifPresent(prod -> product.set(new ProductDTO.ProductBuilder().id(prod.getId()).category(prod.getCategoryId()).price(prod.getPrice()).name(prod.getName()).build()));
		return product.get();
	}
	
	public UserDTO getUser(String userName, String password) {
		UserDTO userDTO = null;
		UserEntity userEntity = null;
		
		if(StringUtils.hasText(password))
			userEntity = userRepo.findByUserNameAndPassword(userName, password);
		else
			userEntity = userRepo.findByUserName(userName);
		
		if(userEntity != null)
			userDTO = new UserDTO.UserBuilder().id(userEntity.getId()).userName(userEntity.getUserName()).userType(userEntity.getUserType()).createdDate(userEntity.getCreatedDate()).build();
		return userDTO;
	}
	
	public Checkout doCheckout(String cacheKey) {
		Double netValue = 0.0;
		Checkout checkout = new Checkout();
		AtomicReference<Integer> discount = new AtomicReference<>(0);
		List<ProductDTO> products = getProducts(cacheKey);
		UserDTO userDTO = getUser(cacheKey, null);
		switch (userDTO.getUserType()) {
		case "staff":
			discount.set(25);
			break;
		case "affiliate":
			discount.set(10);
			break;
		default:
			Date backDate = DateUtils.addDays(new Date(), -2);
			if(userDTO.getCreatedDate().before(backDate))
				discount.set(5);
			break;
		}
		AtomicReference<Double> cartValue = new AtomicReference<>(0.0);
		BinaryOperator<Double> add  = (u, v) -> u + v;
		BinaryOperator<Double> subtract  = (u, v) -> u - v;
		if(discount.get() > 0) {
			products.stream().filter(product -> product.getCategoryId().equalsIgnoreCase(GROCERY_CATEGORY)).collect(Collectors.toList()).stream().forEach(product -> cartValue.getAndAccumulate(Double.valueOf(product.getPrice()), add));
			products.stream().filter(product -> !product.getCategoryId().equalsIgnoreCase(GROCERY_CATEGORY)).collect(Collectors.toList()).stream().forEach(product -> {
				Double price = Double.valueOf(product.getPrice());
				Double discountPrice = price * discount.get()/100;
				cartValue.getAndAccumulate((price - discountPrice), add);
			});
		} else {
			products.stream().forEach(product -> cartValue.getAndAccumulate(Double.valueOf(product.getPrice()), add));
		}
		long hundreds = (long) (cartValue.get()/100);
		netValue = hundreds > 0 ? cartValue.accumulateAndGet((double) (2*hundreds), subtract) : cartValue.get();
		checkout.setProducts(products);
		checkout.setNetValue(netValue);
		return checkout;
	}
	
	public List<ProductDTO> getProducts(String cacheKey) {
		return cacheService.getFromCache(cacheName, cacheKey, List.class);
	}
	
	public void addToCart(ProductDTO product, String cacheKey) {
		List<ProductDTO> allProducts = getProducts(cacheKey);
		if(allProducts == null)
			allProducts = new ArrayList<>();
		allProducts.add(product);
		cacheService.putToCache(cacheName, cacheKey, allProducts);
	}

}
