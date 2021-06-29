package com.api2.schema;

import java.sql.Date;

/**
 * 
 * ProductResponse Class maps the Product class to ProductResponse
 *
 */
public class ProductResponse {

	Integer id;
	String productId;
	String productName;
	Date productExpiryDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getProductExpiryDate() {
		return productExpiryDate;
	}

	public void setProductExpiryDate(Date productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productExpiryDate=" + productExpiryDate + "]";
	}
}