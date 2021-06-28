package com.api2.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api2.model.Product;
import com.api2.model.Response;
import com.api2.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;

	public Response getProductById(String productId) {

		
		if(!(repo.findByProductId(productId).isPresent()))
		{
			return  new Response("FAILED","PRODUCT NOT PRESENT",null);
		}
		return new Response("SUCCESS","PRODUCT PRESENT", repo.findByProductId(productId).get());
	}

	public Response addProduct(Product product) {
		
		if(repo.findByProductId(product.getProductId()).isPresent())
		{
			return new Response("FAILED","PRODUCT ALREADY PRESENT",null);
		}
		repo.save(product);
		return new Response("SUCCESS","PRODUCT SAVED",repo.findByProductId(product.getProductId()).get());
	}

	public Response updateProduct(Product product) {
		
		if(repo.findByProductId(product.getProductId()).isPresent())
		{
			Product updater = repo.findByProductId(product.getProductId()).get();
			updater.setProductExpiryDate(product.getProductExpiryDate());
			updater.setProductName(product.getProductName());
			repo.save(updater);
			return new Response("SUCCESS","PRODUCT UPDATED",repo.findByProductId(product.getProductId()).get());
		}
		
		return  new Response("FAILED","PRODUCT NOT PRESENT",null);
		
	}

	public Response deleteProduct(String productId) {
		
		if(repo.findByProductId(productId).isPresent())
		{
			if((repo.findByProductId(productId).get().getProductExpiryDate())
					.before(Date.valueOf(LocalDate.now())))
			{
				repo.delete(repo.findByProductId(productId).get());
				return new Response("SUCCESS","PRODUCT EXPIRED AND DELETED",null);  //Product Expired and deleted
			}
			return new Response("FAILED","PRODUCT NOT EXPIRED",null);  //Product not expired
		}
		return new Response("FAILED","PRODUCT NOT PRESENT",null);  //Product not present
	}

}
