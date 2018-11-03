package com.capgemini.laps.service.test;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.laps.dao.CustomerDaoImpl;
import com.capgemini.laps.exception.CustomerException;
import com.capgemini.laps.util.DBConnection;

public class DBConnectionTest {

	static CustomerDaoImpl daotest;
	static Connection dbCon;

	@BeforeClass
	public static void initialise() {
		daotest = new CustomerDaoImpl();
		dbCon = null;
	}

	@Before
	public void beforeEachTest() {
		System.out.println("----Starting DBConnection Test Case----\n");
	}

	/**
	 * Test case for Establishing Connection
	 * 
	 * @throws DonorException
	 **/
	@Test
	public void test() throws CustomerException {
		Connection dbCon = DBConnection.getInstance().getConnection();
		Assert.assertNotNull(dbCon);
	}

	@After
	public void afterEachTest() {
		System.out.println("----End of DBConnection Test Case----\n");
	}

	@AfterClass
	public static void destroy() {
		System.out.println("\t----End of Tests----");
		daotest = null;
		dbCon = null;
	}

}
