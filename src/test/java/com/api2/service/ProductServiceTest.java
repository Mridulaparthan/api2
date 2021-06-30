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

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService service;

	@MockBean
	private ProductRepo repository;

	Product product;

	@BeforeEach
	public void setUp() {
		product = new Product();
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate(Date.valueOf("2021-08-12"));

	}

	@Test
	public void getProductByIdTest() {

		when(repository.findByProductId("G1")).thenReturn(Optional.of(product));
		String expected = "Response [responseType=SUCCESS, responseMessage=NOT EXPIRED, productResponse="
				+ "Product [id=1, productId=G1, productName=Noodles, productExpiryDate=2021-08-12]]";
		String actual = service.getProductById("G1").toString();
		assertEquals(expected, actual);
	}

	@Test
	public void addProductTest() {
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.empty());
		when(repository.save(product)).thenReturn(product);
		String expected = "Response [responseType=SUCCESS, responseMessage=PRODUCT SAVED, productResponse="
				+ "Product [id=1, productId=G1, productName=Noodles, productExpiryDate=2021-08-12]]";
		String actual = service.addProduct(product).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void updateProductTest() {
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		product.setProductName("Burger");
		when(repository.save(product)).thenReturn(product);
		String expected = "Response [responseType=SUCCESS, responseMessage=PRODUCT UPDATED, productResponse="
				+ "Product [id=1, productId=G1, productName=Burger, productExpiryDate=2021-08-12]]";
		String actual = service.updateProduct(product).toString();
		assertEquals(expected, actual);

	}

	@Test
	public void deleteProductTest() {
		when(repository.findByProductId(product.getProductId())).thenReturn(Optional.of(product));
		String expected = "Response [responseType=FAILED, responseMessage=PRODUCT NOT EXPIRED, productResponse=null]";
		String actual = service.deleteProduct(product.getProductId()).toString();
		assertEquals(expected, actual);

	}
}