package com.api2.model;

public class Response {

	String responseType;
	String responseMessage;
	Product responseProduct;

	public Response() {
	}

	public Response(String responseType, String responseMessage, Product responseProduct) {
		this.responseType = responseType;
		this.responseMessage = responseMessage;
		this.responseProduct = responseProduct;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Product getResponseProduct() {
		return responseProduct;
	}

	public void setResponseProduct(Product responseProduct) {
		this.responseProduct = responseProduct;
	}

}
