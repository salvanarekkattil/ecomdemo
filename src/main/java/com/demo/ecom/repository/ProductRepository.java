package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}