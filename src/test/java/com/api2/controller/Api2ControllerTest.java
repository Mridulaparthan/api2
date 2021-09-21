package com.api2.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api2.model.Product;
import com.api2.schema.ProductClone;
import com.api2.schema.Response;
import com.api2.service.ProductService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Api2ControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	ProductService productService;

	@InjectMocks
	Api2Controller controller;

	ProductClone productClone;
	Response response;

	private Product ProductCloneToProduct(ProductClone productClone) {
		Product product = new Product();
		product.setId(productClone.getCloneId());
		product.setProductId(productClone.getCloneProductId());
		product.setProductName(productClone.getCloneProductName());
		product.setProductExpiryDate(Date.valueOf(productClone.getCloneProductExpiryDate()));
		return product;
	}

	@BeforeEach
	public void setup() {
		productClone = new ProductClone();
		productClone.setCloneId(1);
		productClone.setCloneProductId("A1");
		productClone.setCloneProductExpiryDate(Date.valueOf(LocalDate.now()).toString());
		productClone.setCloneProductName("Burger");

		response = new Response();
		response.setProductClone(productClone);
		response.setResponseMessage("NOT EXPIRED");
		response.setResponseType("SUCCESS");
	}

	@AfterEach
	public void tearDown() {
		productClone = null;
		response = null;
	}

	@Test
	public void getProductByIdTest() throws Exception {
		when(productService.getProductById(productClone.getCloneProductId())).thenReturn(response);
		mvc.perform(MockMvcRequestBuilders.get("/api2/search/A1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addProductTest() throws Exception {
		response.setResponseMessage("PRODUCT SAVED");
		when(productService.addProduct(this.ProductCloneToProduct(productClone))).thenReturn(response);
		mvc.perform(MockMvcRequestBuilders.post("/api2/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productClone)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateProductTest() throws Exception {
		response.setResponseMessage("PRODUCT UPDATED");
		when(productService.updateProduct(this.ProductCloneToProduct(productClone))).thenReturn(response);
		mvc.perform(MockMvcRequestBuilders.post("/api2/update").content(new Gson().toJson(productClone))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void deleteProductTest() throws Exception
	{
		response.setResponseMessage("PRODUCT EXPIRED AND DELETED");
		response.setProductClone(null);
		productClone.setCloneProductExpiryDate("2020-12-12");
		when(productService.deleteProduct(productClone.getCloneProductId())).thenReturn(response);
		mvc.perform(MockMvcRequestBuilders.get("/api2/delete/A1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}