package com.capgemini.laps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.capgemini.laps.bean.CustomerBean;
import com.capgemini.laps.exception.CustomerException;
import com.capgemini.laps.util.DBConnection;


public class CustomerDaoImpl implements ICustomerDAO{
	

	Logger logger=Logger.getRootLogger();
	public CustomerDaoImpl()
	{
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}
	
	
	//--------------------Adding Customer-----------------------------
	
	@SuppressWarnings("resource")
	public String addCustomerDetails(CustomerBean donor) throws CustomerException 
	{
		Connection connection = DBConnection.getInstance().getConnection();	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		
		String donorId=null;
		
		int queryResult=0;
		try
		{		
			preparedStatement=connection.prepareStatement(QueryMapper.INSERT_QUERY);

			preparedStatement.setString(1,donor.getCustomerName());			
			preparedStatement.setString(2,donor.getAddress());
			preparedStatement.setString(3,donor.getPhoneNumber());
			preparedStatement.setDouble(4,donor.getLoanAmount());			
			
			queryResult=preparedStatement.executeUpdate();
		
			preparedStatement = connection.prepareStatement(QueryMapper.CUSTOMERID_QUERY_SEQUENCE);
			resultSet=preparedStatement.executeQuery();

			if(resultSet.next())
			{
				donorId=resultSet.getString(1);
						
			}
	
			if(queryResult==0)
			{
				logger.error("Insertion failed ");
				throw new CustomerException("Inserting donor details failed ");

			}
			else
			{
				logger.info("Donor details added successfully:");
				return donorId;
			}

		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			logger.error(sqlException.getMessage());
			throw new CustomerException("Tehnical problem occured refer log");
		}

		finally
		{
			try 
			{
				//resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage());
				throw new CustomerException("Error in closing db connection");

			}
		}
		
		
	}
	
	//-----------------view customer--------------------
	

	public CustomerBean viewCustomerDetails(String donorId) throws CustomerException {
		
		Connection connection=DBConnection.getInstance().getConnection();
		
		
		PreparedStatement preparedStatement=null;
		ResultSet resultset = null;
		CustomerBean bean=null;
		
		try
		{
			preparedStatement=connection.prepareStatement(QueryMapper.VIEW_CUSTOMER_DETAILS_QUERY);
			preparedStatement.setString(1,donorId);
			resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				bean = new CustomerBean();
				bean.setCustomerName(resultset.getString(1));
				bean.setAddress(resultset.getString(2));
				bean.setPhoneNumber(resultset.getString(3));
			//	bean.setDonationDate(resultset.getDate(4));
				bean.setLoanAmount(resultset.getDouble(5));
				
			}
			
			if( bean != null)
			{
				logger.info("Record Found Successfully");
				return bean;
			}
			else
			{
				logger.info("Record Not Found Successfully");
				return null;
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			throw new CustomerException(e.getMessage());
		}
		finally
		{
			try 
			{
				resultset.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new CustomerException("Error in closing db connection");

			}
		}
	
		
	}
	
	//-----------retrieve all----------------
public List<CustomerBean> retriveAllDetails() throws CustomerException {
		
		Connection con=DBConnection.getInstance().getConnection();
		int donorCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<CustomerBean> donorList=new ArrayList<CustomerBean>();
		try
		{
			ps=con.prepareStatement(QueryMapper.RETRIVE_ALL_QUERY);
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				CustomerBean bean=new CustomerBean();
				bean.setCustomerName(resultset.getString(1));
				bean.setAddress(resultset.getString(2));
				bean.setPhoneNumber(resultset.getString(3));
				//bean.setDonationDate(resultset.getDate(4));
				bean.setLoanAmount(resultset.getDouble(5));
				donorList.add(bean);
				
				donorCount++;
			}			
			
		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new CustomerException("Tehnical problem occured. Refer log");
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new CustomerException("Error in closing db connection");

			}
		}
		
		if( donorCount == 0)
			return null;
		else
			return donorList;
	}

}



