package com.api2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.api2.model.Product;
import com.api2.repository.ProductRepo;
import com.api2.schema.ProductResponse;
import com.api2.schema.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService service;

	@MockBean
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
		product.setProductExpiryDate(Date.valueOf("2021-08-12"));

		response = new Response();
		response.setResponseType("SUCCESS");
		response.setResponseMessage("NOT EXPIRED");
		response.setProductResponse(this.getProductResponse(product));
	}

	@Test
	public void getProductByIdTest() {

		when(repository.findByProductId("G1")).thenReturn(Optional.of(product));
		assertEquals(response.toString(), service.getProductById("G1").toString());
	}

	@Test
	public void addProductTest() {
		response.setResponseMessage("PRODUCT SAVED");
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.empty());
		when(repository.save(product)).thenReturn(product);
		assertEquals(response.toString(), service.addProduct(product).toString());
	}

	@Test
	public void updateProductTest() {
		product.setProductName("Burger");
		response.setResponseMessage("PRODUCT UPDATED");
		response.setProductResponse(this.getProductResponse(product));
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		when(repository.save(product)).thenReturn(product);
		assertEquals(response.toString(), service.updateProduct(product).toString());
	}

	@Test
	public void deleteProductTest() {
		response.setResponseType("FAILED");
		response.setResponseMessage("PRODUCT NOT EXPIRED");
		response.setProductResponse(null);
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		assertEquals(response.toString(), service.deleteProduct(product.getProductId()).toString());
	}
}