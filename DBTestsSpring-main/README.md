## Project's Title:
	DB Tests

## Project Description:
	
	Database Tests:

		Relations should exist:
	One Customer should have an Address
	One Customer should have between 0 and X random Orders
	One Order should have between 1 and X Products

		Description:
	Create customer tests that validate the data in the Customers table. 
	Verify:
		Verify that customer's table is not empty before tests
		that there are no customers without an address
		that there are no customers with the same phone or email
		create a new user, save it and verify that it was saved successfully 
		create a test that verifies that you cannot create and save a user without the mandatory fields set for the table

	Create tests for Address data:
		Verify that the addresses table is not empty before tests
		get a random customer and verify that his addresses have all mandatory fields filled
		create a test that verifies that you cannot create and save an address without the mandatory fields set for the table
	
	Create tests for Orders data
		Verify that the orders table is not empty before each test and fail the test if it is
		get X random customers and verify that their orders (if any) have all mandatory fields filled
		get X random orders and verify that they have a customer
		create a new order and verify that it was saved successfully 
		create a test that verifies that you cannot create and save an order without the mandatory fields set for the table
	
	Create tests for Products data
		verify that the products table is not empty before tests 
		get X random orders and verify that their products (if any) have all mandatory fields filled.
		create a new product and verify that it was saved successfully and there are no orders for it (as it was just created)
		create a test that verifies that you cannot create and save a product without the mandatory fields set for the table
	



## Table of Contents:
	Task.txt - description of the task
	README.md file
	estafet.mappingdata project
	.gitignore file

## How to Install and Run the Project:
	SQL: Local database environment setup
	 
## How to Use the Project:
	IDE need to execute the java project

## Useful links:
	

## Add a License
	no needed


