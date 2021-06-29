package com.api2.service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api2.model.Product;
import com.api2.repository.ProductRepo;
import com.api2.schema.ProductResponse;
import com.api2.schema.Response;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;

	public Response getProductById(String productId) {

		Optional<Product> product = repo.findByProductId(productId);
		if (product.isPresent()) {
			ProductResponse productResponse = this.getProductResponse(product.get());
			String status = new String();
			if (productResponse.getProductExpiryDate().before(Date.valueOf(LocalDate.now()))) {
				status = "EXPIRED";
			} else {
				status = "NOT EXPIRED";
			}
			return this.getResponse("SUCCESS", status, productResponse);
		}
		return this.getResponse("FAILED", "PRODUCT ABSENT", null);
	}

	public Response addProduct(Product product) {

		if (repo.findByProductId(product.getProductId()).isPresent()) {
			return this.getResponse("FAILED", "PRODUCT ALREADY PRESENT", null);
		}
		ProductResponse productResponse = this.getProductResponse(repo.save(product));
		return this.getResponse("SUCCESS", "PRODUCT SAVED", productResponse);
	}

	public Response updateProduct(Product product) {
		Optional<Product> tempProduct = repo.findByProductId(product.getProductId());
		if (tempProduct.isPresent()) {
			Product updater = tempProduct.get();
			updater.setProductExpiryDate(product.getProductExpiryDate());
			updater.setProductName(product.getProductName());
			// repo.delete(tempProduct.get());
			ProductResponse productResponse = this.getProductResponse(repo.save(updater));
			return this.getResponse("SUCCESS", "PRODUCT UPDATED", productResponse);
		}
		return this.getResponse("FAILED", "PRODUCT NOT PRESENT", null);
	}

	public Response deleteProduct(String productId) {

		Optional<Product> product = repo.findByProductId(productId);

		if (product.isPresent()) {
			if ((product.get().getProductExpiryDate()).before(Date.valueOf(LocalDate.now()))) {
				repo.delete(product.get());
				return this.getResponse("SUCCESS", "PRODUCT EXPIRED AND DELETED", null); // Product Expired and deleted
			}
			return this.getResponse("FAILED", "PRODUCT NOT EXPIRED", null); // Product not expired
		}
		return this.getResponse("FAILED", "PRODUCT NOT PRESENT", null); // Product not present
	}

	/**
	 * Method to get Response
	 * 
	 * @param responseType    input response type
	 * @param responseMessage input response message
	 * @param product         input the product
	 * @return response returns response with these details
	 */
	private Response getResponse(String responseType, String responseMessage, ProductResponse product) {
		Response response = new Response();
		response.setResponseType(responseType);
		response.setResponseMessage(responseMessage);
		response.setProductResponse(product);
		return response;
	}

	/**
	 * Method to map Product to ProductResponse
	 * 
	 * @param product input product
	 * @return productResponse returns products mapped to product response
	 */
	private ProductResponse getProductResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setProductExpiryDate(product.getProductExpiryDate());
		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());
		return productResponse;

	}

}