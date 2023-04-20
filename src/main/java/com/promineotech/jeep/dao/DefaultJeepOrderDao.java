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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultJeepOrderDao implements JeepOrderDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	// this in JODao.java
	public Customer fetchCustomer(String customerId) {
		
		String sql= ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE customer_id = :customer_id";
				
		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);
		
		return jdbcTemplate.query(sql, params, new CustomerResultSetExtractor());
	}
	
	private RowCallbackHandler CustomerResultSetExtractor() {
		// TODO Auto-generated method stub
		return null;
	}

	class CustomerRestultSetExtractor implements ResultSetExtractor<Customer> {
//		new ResultSetExtractor<Customer>() {

			@Override
			public Customer extractData(ResultSet rs) 
				throws SQLException, DataAccessException {
				return null;
			}
	}
}

