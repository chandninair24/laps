package com.capgemini.laps.dao;

import java.util.List;

import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.exception.CustomerException;

public interface ICustomerDAO {

	public String addCustomerDetails(CustomerBean donor) throws CustomerException;
	public CustomerBean viewCustomerDetails(String donorId) throws CustomerException;
	public List<CustomerBean> retriveAllDetails() throws CustomerException;
}
