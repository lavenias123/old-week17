package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@Component
public class DefaultJeepOrderDao implements JeepOrderDao {
// Error: So I selected from choice of corrections to: 
// which I believe was added on lines 70 - 74 unimplemented method for DefaultJeepOrderDao
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	// this in JODao.java
	
	/**
	 * 
	 */
	@Override 
	// causes error 
	public Customer fetchCustomer(String customerId) {
		
		String sql= ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE customer_id = :customer_id";
				
		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);
		
		// Error: Cannot return a void result
		return jdbcTemplate.query(sql, params, new CustomerResultSetExtractor());
	}

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	

	class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
//		new ResultSetExtractor<Customer>() {

			@Override
			public Customer extractData(ResultSet rs) 
				throws SQLException, DataAccessException {
			rs.next();
				
			//formatter:off
			return Customer.builder()
					.customerId(rs.getString("customer_id"))
					.customerPK(rs.getLong("customer_pk"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.phone(rs.getString("phone"))
					.build();	
			//formatter:on
			}
	}

	
}

