package com.api2.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseTest {

	Response response;
	ProductClone productClone;
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		productClone = new ProductClone();
		productClone.setCloneId(1);
		productClone.setCloneProductId("A1");
		productClone.setCloneProductName("Rice");
		productClone.setCloneProductExpiryDate(today);

		response = new Response();
		response.setProductClone(productClone);
		response.setResponseType("SUCCESS");
		response.setResponseMessage("PRODUCT SAVED");
	}

	@Test
	public void getProductResponse() {
		assertEquals(productClone, response.getProductClone(), "getProductResponse not implemented properly");
	}

	@Test
	public void getResponseMessage() {
		assertEquals("PRODUCT SAVED", response.getResponseMessage(), "getResponseMessage not implemented properly");
	}

	@Test
	public void getResponseType() {
		assertEquals("SUCCESS", response.getResponseType(), "getResponseType not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals(
				"Response [responseType=SUCCESS, responseMessage=PRODUCT SAVED, productClone=ProductClone [cloneId=1, cloneProductId=A1, cloneProductName=Rice, cloneProductExpiryDate="
						+ today + "]]",
				response.toString(), "toString not implemented properly");
	}
}