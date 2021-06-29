package com.api2.service;

import com.api2.model.Product;
import com.api2.schema.Response;

/**
 * 
 * Product Service interface
 *
 */
public interface ProductService {
	/**
	 * Method to Get Product by Id
	 * 
	 * @param productId input product Id
	 * @return response returns response with product and expiration status
	 */
	public Response getProductById(String productId);

	/**
	 * Method to Add Product
	 * 
	 * @param product input product to be added
	 * @return response returns the added product and status
	 */
	public Response addProduct(Product product);

	/**
	 * Method to Update Product
	 * 
	 * @param product input product to update
	 * @return response returns the updated product and status
	 */
	public Response updateProduct(Product product);

	/**
	 * Method to Delete Product
	 * 
	 * @param productId input product id
	 * @return response returns whether product is deleted or if its not
	 */
	public Response deleteProduct(String productId);

}