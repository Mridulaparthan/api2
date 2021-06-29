package com.api2.schema;
/**
 * 
 * Response Class sends the response back for the requests
 *
 */
public class Response {

	String responseType;
	String responseMessage;
	ProductResponse productResponse;

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

	public ProductResponse getProductResponse() {
		return productResponse;
	}

	public void setProductResponse(ProductResponse productResponse) {
		this.productResponse = productResponse;
	}

	@Override
	public String toString() {
		return "Response [responseType=" + responseType + ", responseMessage=" + responseMessage + ", productResponse="
				+ productResponse + "]";
	}

}

