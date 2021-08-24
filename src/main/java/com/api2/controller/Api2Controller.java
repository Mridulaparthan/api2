package com.api2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api2.model.Product;
import com.api2.schema.Response;
import com.api2.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Api2Controller Class. Contains all the Handler Methods.
 *
 */
@RestController
@Api
@RequestMapping("/api2")
public class Api2Controller {

	@Autowired
	ProductService serv;

	/**
	 * Returns a response for the productId.
	 * 
	 * @param productId
	 * @return response
	 */
	@GetMapping("/search/{productId}")
	@ApiOperation(value = "Search by Product ID")
	public ResponseEntity<Response> getProductById(@PathVariable String productId) {
		return new ResponseEntity<Response>(serv.getProductById(productId), HttpStatus.OK);
	}

	/**
	 * Saves the product and returns the response containing the added product if it
	 * was added.
	 * 
	 * @param product
	 * @return response
	 */
	@PostMapping("/add")
	@ApiOperation(value = "Add Product")
	public ResponseEntity<Response> addProduct(@RequestBody Product product) {

		return new ResponseEntity<Response>(serv.addProduct(product), HttpStatus.OK);
	}

	/**
	 * Updates the product if the product was present and returns the response
	 * containing updated product.
	 * 
	 * @param product
	 * @return response
	 */
	@PostMapping("/update")
	@ApiOperation(value = "Update Product")
	public ResponseEntity<Response> updateProduct(@RequestBody Product product) {

		return new ResponseEntity<Response>(serv.updateProduct(product), HttpStatus.OK);
	}

	/**
	 * Delete the product by Id and returns the response containing the status.
	 * 
	 * @param productId
	 * @return response
	 */
	@GetMapping("/delete/{productId}")
	@ApiOperation(value = "Delete Product")
	public ResponseEntity<Response> deleteProduct(@PathVariable String productId) {

		return new ResponseEntity<Response>(serv.deleteProduct(productId), HttpStatus.OK);
	}

}