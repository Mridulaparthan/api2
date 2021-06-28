package com.api2.service;

import com.api2.model.Product;
import com.api2.model.Response;

public interface ProductService {
	public Response getProductById(String productId);
	public Response addProduct(Product product);
	public Response updateProduct(Product product);
	public Response deleteProduct(String productId);
	
}
