package com.capgemini.laps.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.dao.CustomerDaoImpl;
import com.capgemini.laps.exception.CustomerException;

public class CsutomerDaoTest {
	static CustomerDaoImpl dao;
	static CustomerBean customer;

	@BeforeClass
	public static void initialize() {
		System.out.println("in before class");
		dao = new CustomerDaoImpl();
		customer = new CustomerBean();
	}

	@Test
	public void testAddCustomerDetails() throws CustomerException {

		assertNotNull(dao.addCustomerDetails(customer));

	}

	/************************************
	 * Test case for addCustomerDetails()
	 * 
	 ************************************/

	@Ignore
	@Test
	public void testAddDonarDetails1() throws CustomerException {
		// increment the number next time you test for positive test case
		assertEquals(1001, dao.addCustomerDetails(customer));
	}

	/************************************
	 * Test case for addDonarDetails()
	 * 
	 ************************************/

	@Test
	public void testAddCustomerDetails2() throws CustomerException {

		customer.setCustomerName("Shashwathi");
		customer.setPhoneNumber("9876543210");
		customer.setAddress("whitefield");
		customer.setLoanAmount(5000);
		assertTrue("Data Inserted successfully",
				Integer.parseInt(dao.addCustomerDetails(customer)) > 1000);

	}

	/********************************************
	 * Test case for retriveAllDetails()
	 ************************************************/
	@Test
	public void testViewAll() throws CustomerException {
		assertNotNull(dao.retriveAllDetails());
	}

	/****************************************************
	 * Test case for viewById()
	 ******************************************************/

	@Test
	public void testById() throws CustomerException {
		assertNotNull(dao.viewCustomerDetails("1010"));
	}

	@Ignore
	@Test
	public void testById1() throws CustomerException {
		assertEquals("TestName", dao.viewCustomerDetails("1010").getCustomerName());
	}

}
