package com.capgemini.laps.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.exception.CustomerException;
import com.capgemini.laps.service.CustomerServiceImpl;
import com.capgemini.laps.service.ICustomerService;


public class MainApp {
	static Scanner sc = new Scanner(System.in);

	
	static ICustomerService customerService = null;
	static CustomerServiceImpl customerServiceImpl = null;
	static Logger logger = Logger.getRootLogger();


	public static void main(String[] args)
	{
		PropertyConfigurator.configure("resources//log4j.properties");
		CustomerBean customerBean=null;
		String customerId = null;
		int option = 0;
System.out.println("LOAN APPLICATION PROCESSING ");
System.out.println("LOGIN IN AS");
System.out.println("1. CUSTOMER");
System.out.println("2. LOAN APLLICATION PROCESSING DEPARTMENT");
System.out.println("Enter your choice");
option=sc.nextInt();


	switch(option)
	{
	case 1:
	{
		int choice=0;
		System.out.println("WELCOME");
		System.out.println("1. APPLY LOAN");
		System.out.println("2. VIEW LOAN APPLICATION STATUS BY CUSTOMER ID");
		System.out.println("3. EXIT");
		System.out.println("ENTER YOUR CHOICE");
		choice=sc.nextInt();
		while(true)
	{
		switch(choice)
		{
		case 1:
			{while (customerBean == null) {
				customerBean = populateCustomerBean();
				// System.out.println(donorBean);
			}

			try {
				customerService = new CustomerServiceImpl();
				customerId = customerService.addCustomerDetails(customerBean);

				System.out
						.println("customer details  has been successfully registered ");
				System.out.println("Customer ID Is: " + customerId);

			} catch (CustomerException customerException) {
				logger.error("exception occured", customerException);
				System.out.println("ERROR : "
						+ customerException.getMessage());
			} finally {
				customerId = null;
				customerService = null;
				customerBean = null;
			}

			break;
			}
			
		case 2:
		{
			customerServiceImpl = new CustomerServiceImpl();

			System.out.println("Enter numeric customer id:");
			customerId = sc.next();

			while (true) {
				if (customerServiceImpl.validateCustomerId(customerId)) {
					break;
				} else {
					System.err
							.println("Please enter numeric customer id only, try again");
				    customerId = sc.next();
				}
			}

			customerBean = getCustomerDetails(customerId);

			if (customerBean != null) {
				System.out.println("Name             :"
						+ customerBean.getCustomerName());
				System.out.println("Address          :"
						+ customerBean.getAddress());
				System.out.println("Phone Number     :"
						+ customerBean.getPhoneNumber());
//			
				System.out.println("Loan Amount  :"
						+ customerBean.getLoanAmount());
			} else {
				System.err
						.println("There are no loan details associated with customer id "
								+ customerId);
			}
			customerServiceImpl = new CustomerServiceImpl();

			try {
				customerServiceImpl.validateCustomer(customerBean);
				System.out.println("LOAN APPROVED");
				return;
			} catch (CustomerException customerException) {
				//logger.error("exception occured", customerException);
				System.err.println("LOAN STATUS:NOT APPROVED");
				//System.err.println(customerException.getMessage() + " \n Try again..");

			}
			break;
		}
		case 3:
		{
			System.out.print("Exit Trust Application");
			System.exit(0);
			break;
		
			
		}
		default:
		{
			System.out.println("Sorry Invalid Choice");
		}
		
	}
		
	}
	}
	case 2:
	{
		int opt=0;
		System.out.println("WELCOME");
		System.out.println("1. VIEW ALL APPLICATIONS");
		System.out.println("2. VIEW APLLICATION BY CUSTOMER ID");
		System.out.println("3. PROCESS THE APPLICATION");
		System.out.println("4. EXIT");
		System.out.println("ENTER YOUR OPTION");
		opt=sc.nextInt();
		while(true)
		{
			switch(opt)
			{
			case 1:
			{
				customerService = new CustomerServiceImpl();
				try {
					List<CustomerBean> customerList = new ArrayList<CustomerBean>();
					customerList = customerService.retriveAll();

					if (customerList != null) {
						Iterator<CustomerBean> i = customerList.iterator();
						while (i.hasNext()) {
							System.out.println(i.next());
						}
					} else {
						System.out
								.println("Nobody has applied for loan yet.");
					}

				}

				catch (CustomerException e) {

					System.out.println("Error  :" + e.getMessage());
				}

				break;
			}
			
			case 2:
			{
				customerServiceImpl = new CustomerServiceImpl();

				System.out.println("Enter numeric customer id:");
				customerId = sc.next();

				while (true) {
					if (customerServiceImpl.validateCustomerId(customerId)) {
						break;
					} else {
						System.err
								.println("Please enter numeric customer id only, try again");
					    customerId = sc.next();
					}
				}

				customerBean = getCustomerDetails(customerId);

				if (customerBean != null) {
					System.out.println("Name             :"
							+ customerBean.getCustomerName());
					System.out.println("Address          :"
							+ customerBean.getAddress());
					System.out.println("Phone Number     :"
							+ customerBean.getPhoneNumber());
//				
					System.out.println("Loan Amount  :"
							+ customerBean.getLoanAmount());
				} else {
					System.err
							.println("There are no loan details associated with customer id "
									+ customerId);
				}

				break;
			}
			
			case 3:
			{
				customerServiceImpl = new CustomerServiceImpl();

				System.out.println("Enter numeric customer id:");
				customerId = sc.next();

				while (true) {
					if (customerServiceImpl.validateCustomerId(customerId)) {
						break;
					} else {
						System.err
								.println("Please enter numeric customer id only, try again");
					    customerId = sc.next();
					}
				}

				customerBean = getCustomerDetails(customerId);

				if (customerBean != null) {
					System.out.println("Name             :"
							+ customerBean.getCustomerName());
					System.out.println("Address          :"
							+ customerBean.getAddress());
					System.out.println("Phone Number     :"
							+ customerBean.getPhoneNumber());
//				
					System.out.println("Loan Amount  :"
							+ customerBean.getLoanAmount());
				} else {
					System.err
							.println("There are no loan details associated with customer id "
									+ customerId);
				}
				
				
				customerServiceImpl = new CustomerServiceImpl();

				try {
					customerServiceImpl.validateCustomer(customerBean);
					return;
				} catch (CustomerException customerException) {
					logger.error("exception occured", customerException);
					System.err.println("LOAN STATUS:NOT APPROVED");
					System.err.println(customerException.getMessage() + " \n Try again..");

				break;
			}
			}
			
			case 4:
			{
				System.out.print("Exit Trust Application");
				System.exit(0);
				break;
			}
			default:
			{
				System.out.println("Sorry Invalid Choice");
			}
			
			}
		}
		
	}
	}
	
		

		

	}


	private static CustomerBean getCustomerDetails(String customerId) {
		CustomerBean customerBean = null;
		customerService = new CustomerServiceImpl();

		try {
			customerBean = customerService.viewCustomerDetails(customerId);
		} catch (CustomerException customerException) {
			logger.error("exception occured ", customerException);
			System.out.println("ERROR : " + customerException.getMessage());
		}

		customerService = null;
		return customerBean;
	}


	private static CustomerBean populateCustomerBean() {
		CustomerBean customerBean = new CustomerBean();;

		System.out.println("\n Enter Details");

		System.out.println("Enter customer name: ");
		customerBean.setCustomerName(sc.next());

		System.out.println("Enter customer contact: ");
		customerBean.setPhoneNumber(sc.next());

		System.out.println("Enter customer address: ");
		customerBean.setAddress(sc.next());

		System.out.println("Enter loan amount: ");

		try {
			customerBean.setLoanAmount(sc.nextFloat());
		} catch (InputMismatchException inputMismatchException) {
			sc.nextLine();
			System.err
					.println("Please enter a numeric value for loan amount, try again");
			}

		customerServiceImpl = new CustomerServiceImpl();

		try {
			customerServiceImpl.validateCustomer(customerBean);
			return customerBean;
		} catch (CustomerException customerException) {
			logger.error("exception occured", customerException);
			System.err.println("Invalid data:");
			System.err.println(customerException.getMessage() + " \n Try again..");
			System.exit(0);

		}
		return null;

		
	}

}
