package com.capgemini.laps.service;

import java.util.List;

import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.exception.CustomerException;



public interface ICustomerService {
	public String addCustomerDetails(CustomerBean customer) throws CustomerException;
	public CustomerBean viewCustomerDetails(String customerId) throws CustomerException;
	public List<CustomerBean> retriveAll()throws CustomerException;


}
