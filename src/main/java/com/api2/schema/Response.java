package com.api2.schema;

/**
 * 
 * Response Class. Contains responseType, responseMessage and productResponse as
 * variables.
 *
 */
public class Response {

	String responseType;
	String responseMessage;
	ProductClone productClone;

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

	public ProductClone getProductClone() {
		return productClone;
	}

	public void setProductClone(ProductClone productClone) {
		this.productClone = productClone;
	}

	@Override
	public String toString() {
		return "Response [responseType=" + responseType + ", responseMessage=" + responseMessage + ", productClone="
				+ productClone + "]";
	}

}