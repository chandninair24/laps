package com.capgemini.laps.dao;
public interface QueryMapper {
	
	public static final String RETRIVE_ALL_QUERY="SELECT customer_name,address,phone_number,loan_amount FROM customer_details";
	public static final String VIEW_CUSTOMER_DETAILS_QUERY="SELECT customer_name,address,phone_number,loan_amount FROM customer_details WHERE  customer_id=?";
	public static final String INSERT_QUERY="INSERT INTO customer_details VALUES(customerId_sequence.NEXTVAL,?,?,?,?)";
	public static final String CUSTOMERID_QUERY_SEQUENCE="SELECT customerId_sequence.CURRVAL FROM DUAL";
	
	
}
