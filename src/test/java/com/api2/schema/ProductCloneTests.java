package com.api2.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.api2.model.Product;

public class ProductCloneTests {
	
	ProductClone productClone;
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		productClone = new ProductClone();
		productClone.setCloneId(1);
		productClone.setCloneProductId("A1");
		productClone.setCloneProductName("Rice");
		productClone.setCloneProductExpiryDate(today);
	}

	@Test
	public void getId() {

		assertEquals(1, productClone.getCloneId(), "getId not implemented properly");
	}

	@Test
	public void getProductId() {
		assertEquals("A1", productClone.getCloneProductId(), "getProductId not implemented properly");
	}

	@Test
	public void getProductName() {
		assertEquals("Rice", productClone.getCloneProductName(), "getProductName not implemented properly");
	}

	@Test
	public void getProductExpiryDate() {
		assertEquals(today, productClone.getCloneProductExpiryDate(), "getProductExpiryDate not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals("ProductClone [cloneId=1, cloneProductId=A1, cloneProductName=Rice, cloneProductExpiryDate=" + today + "]",
				productClone.toString(), "toString not implemented properly");
	}

}