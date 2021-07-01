package com.api2.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api2.model.Product;
import com.api2.repository.ProductRepo;
import com.api2.schema.ProductResponse;
import com.api2.schema.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductRepo repo;

	public Response getProductById(String productId) {
		log.info("Called getProductById service");

		Optional<Product> product = repo.findByProductId(productId);
		if (product.isPresent()) {
			ProductResponse productResponse = this.getProductResponse(product.get());
			String status = new String();
			if (productResponse.getProductExpiryDate().before(Date.valueOf(LocalDate.now()))) {
				status = "EXPIRED";
			} else {
				status = "NOT EXPIRED";
			}
			log.info("Product is Present and Returned");
			log.info("Exited getProductById service");
			return this.getResponse("SUCCESS", status, productResponse);
		}
		log.info("Product is absent");
		log.info("Exited getProductById service");
		return this.getResponse("FAILED", "PRODUCT ABSENT", null);
	}

	public Response addProduct(Product product) {

		log.info("Called addProduct Service");
		if (repo.findByProductId(product.getProductId()).isPresent()) {
			log.info("Product was not added because it was already present");
			log.info("Exited addProduct service");
			return this.getResponse("FAILED", "PRODUCT ALREADY PRESENT", null);
		}
		ProductResponse productResponse = this.getProductResponse(repo.save(product));
		log.info("Product was added");
		log.info("Exited addProduct service");
		return this.getResponse("SUCCESS", "PRODUCT SAVED", productResponse);
	}

	public Response updateProduct(Product product) {
		log.info("Called updateProductService");
		Optional<Product> tempProduct = repo.findByProductId(product.getProductId());
		if (tempProduct.isPresent()) {
			Product updater = tempProduct.get();
			updater.setProductExpiryDate(product.getProductExpiryDate());
			updater.setProductName(product.getProductName());
			ProductResponse productResponse = this.getProductResponse(repo.save(updater));
			log.info("Product was updated");
			log.info("Exited updateProduct service");
			return this.getResponse("SUCCESS", "PRODUCT UPDATED", productResponse);
		}
		log.info("Update failed because product was not present");
		log.info("Exited updateProduct service");
		return this.getResponse("FAILED", "PRODUCT NOT PRESENT", null);
	}

	public Response deleteProduct(String productId) {
		log.info("Called deleteProduct service");

		Optional<Product> product = repo.findByProductId(productId);

		if (product.isPresent()) {
			if ((product.get().getProductExpiryDate()).before(Date.valueOf(LocalDate.now()))) {
				repo.delete(product.get());
				log.info("Product was expired and Deleted");
				log.info("Exited deleteProduct service");
				return this.getResponse("SUCCESS", "PRODUCT EXPIRED AND DELETED", null); // Product Expired and deleted
			}
			log.info("Product was not Deleted because product was not expired");
			log.info("Exited deleteProduct service");
			return this.getResponse("FAILED", "PRODUCT NOT EXPIRED", null); // Product not expired
		}
		log.info("Product was not Deleted because product was not present");
		log.info("Exited deleteProduct service");
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