package com.api2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api2.model.Product;
import com.api2.repository.ProductRepo;
import com.api2.schema.ProductResponse;
import com.api2.schema.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest2 {

	@Autowired
	private ProductService service;


	@Spy
	private ProductRepo repository;

	Product product;
	Response response;
	ProductResponse productResponse;

	private ProductResponse getProductResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setProductExpiryDate(product.getProductExpiryDate());
		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());
		return productResponse;

	}

	@BeforeEach
	public void setUp() {
		product = new Product();
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()));

		response = new Response();
		response.setResponseType("SUCCESS");
		response.setResponseMessage("NOT EXPIRED");
		response.setProductResponse(this.getProductResponse(product));
	}


	@Test
	public void updateProductTest() {
		product.setProductName("Burger");
		response.setResponseMessage("PRODUCT UPDATED");
		response.setProductResponse(this.getProductResponse(product));
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		when(repository.save(product)).thenReturn(product);
		Response productResponse = service.updateProduct(product);
		assertNotNull(productResponse);
		System.out.println(productResponse.getResponseMessage());
		assertEquals("PRODUCT UPDATED", productResponse.getResponseMessage());
	}

	@Test
	public void deleteProductTest() {
		response.setResponseType("FAILED");
		response.setResponseMessage("PRODUCT NOT EXPIRED");
		response.setProductResponse(null);
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		Response productResponse = service.deleteProduct(product.getProductId());
		assertNotNull(productResponse);
		assertEquals("PRODUCT NOT EXPIRED", productResponse.getResponseMessage());
	}
}