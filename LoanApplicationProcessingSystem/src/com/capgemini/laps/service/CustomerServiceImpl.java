package com.capgemini.laps.service;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.dao.CustomerDaoImpl;
import com.capgemini.laps.dao.ICustomerDAO;
import com.capgemini.laps.exception.CustomerException;



public class CustomerServiceImpl implements ICustomerService {
	
	ICustomerDAO customerDao;
	
	//adding donor to database calls dao method addDonorDetails(donor)
	

	public String addCustomerDetails(CustomerBean customer) throws CustomerException {
		customerDao=new CustomerDaoImpl();	
		String customerSeq;
		customerSeq= customerDao.addCustomerDetails(customer);
		return customerSeq; 
	}

	//	calls dao method viewDonorDetails(donorId)
	
	public CustomerBean viewCustomerDetails(String customerId) throws CustomerException {
		customerDao=new CustomerDaoImpl();
		CustomerBean bean=null;
		bean=customerDao.viewCustomerDetails(customerId);
		return bean;
	}

	// calls dao method retriveAllDetails()
	 
	public List<CustomerBean> retriveAll() throws CustomerException {
		customerDao=new CustomerDaoImpl();
		List<CustomerBean> donorList=null;
		donorList=customerDao.retriveAllDetails();
		return donorList;
	}
	
	
	// validates the DonorBean object
	 
	public void validateCustomer(CustomerBean bean) throws CustomerException
	{
		List<String> validationErrors = new ArrayList<String>();

		//Validating donor name
		if(!(isValidName(bean.getCustomerName()))) {
			validationErrors.add("\n Customer Name Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		//Validating address
		if(!(isValidAddress(bean.getAddress()))){
			validationErrors.add("\n Address Should Be Greater Than 5 Characters \n");
		}
		//Validating Phone Number
		if(!(isValidPhoneNumber(bean.getPhoneNumber()))){
			validationErrors.add("\n Phone Number Should be in 10 digit \n");
		}
		//Validating Donation Amount
		if(!(isValidAmount(bean.getLoanAmount()))){
			validationErrors.add("\n Amount Should be a positive Number \n" );
		}
		
		if(!validationErrors.isEmpty())
			throw new CustomerException(validationErrors +"");
	}

	public boolean isValidName(String donorName){
		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher=namePattern.matcher(donorName);
		return nameMatcher.matches();
	}
	public boolean isValidAddress(String address){
		return (address.length() > 6);
	}
	
	public boolean isValidPhoneNumber(String phoneNumber){
		Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(phoneNumber);
		return phoneMatcher.matches();
		
	}
	public boolean isValidAmount(double amount){
		return (amount>0);
	}
	public boolean validateCustomerId(String customerId) {
		
		Pattern idPattern = Pattern.compile("[0-9]{1,4}");
		Matcher idMatcher = idPattern.matcher(customerId);
		
		if(idMatcher.matches())
			return true;
		else
			return false;		
	}
}



	
	


