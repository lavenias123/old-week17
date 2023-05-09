package com.promineotech.jeep.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.OptionType;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;

import lombok.extern.slf4j.Slf4j;
 
@Component
public class DefaultJeepOrderDao implements JeepOrderDao {
	// added below unimplemented method to remove red lines under DefaultJeepOrderDao
	
	@Override
	public Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine, Tire tire, BigDecimal price, List<Option> options) {
		return null;
	}
	
	@Override
	public Optional<Order> createOrder(OrderRequest orderRequest) {
		return Optional.empty();
	}
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	// this in JODao.java
	
	
	@Override
	public List<Option> fetchOptions(List<String> optionIds) {
		
		if(optionIds.isEmpty()) {
			return new LinkedList<>();
		}
		
		
		Map<String, Object> params = new HashMap<>();
		
		// @formatter: off
		String sql = ""
				+ "SELECT * "
				+ "FROM options "
				+ "WHERE option_id IN(";
		// @formatter: on
		
		for(int index = 0; index < optionIds.size(); index++) {
			String key = "option_" + index;
			sql += ":" + key + ", ";
//			System.out.println("key=" + key + ", sql=" + sql);
			params.put(key, optionIds.get(index));
		}
		
		sql = sql.substring(0, sql.length() - 2);
		sql += ")";
		
		return jdbcTemplate.query(sql, params, new RowMapper<Option>() {

			@Override
			public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
				// formatter: off
				return Option.builder()
						.category(OptionType.valueOf(rs.getString("category")))
						.manufacturer(rs.getString("manufacturer"))
						.name(rs.getString("name"))
						.optionId(rs.getString("option_id"))
						.optionPK(rs.getLong("option_pk"))
						.price(rs.getBigDecimal("price"))
						.build();
				// formatter: on
			}});
	}
	
 
	/**
	 * 
	 */
	@Override 
	public Optional<Customer> fetchCustomer(String customerId) {
		
		String sql= ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE customer_id = :customer_id";
				
		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
	}

//	@Override
//	public Optional<Order> createOrder(OrderRequest orderRequest) {
//		String sql= ""
//				+ "SELECT * "
//				+ "FROM orders "
//				+ "WHERE order_id = :order_id";
//		
//		Map<String, Object> params = new HashMap<>();
//		params.put("order_id", orderId);
//		
////		return Optional.ofNullable(
////				jdbcTemplate.query(sql, params, new OrderResultSetExtractor()));
//	}
//	
// inner class
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


	@Override
	public Optional<Jeep> fetchModel(JeepModel model, String trim, int doors) {
		// @formatter:off
		String sql= ""
				+ "SELECT * "
				+ "FROM models "
				+ "WHERE model_id = :model_id "
				+ "AND trim_level = :trim_level " 
				+ "AND num_doors = :num_doors";
		// @formatter:on		
		Map<String, Object> params = new HashMap<>();
		params.put("model_id", model.toString());
		params.put("trim_level", trim);
		params.put("num_doors", doors);
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new ModelResultSetExtractor()));
		
	}

	
	@Override
	public Optional<Color> fetchColor(String colorId) {
		// @formatter:off
		String sql= ""
				+ "SELECT * "
				+ "FROM colors "
				+ "WHERE color_id = :color_id";
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("color_id", colorId);
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new ColorResultSetExtractor()));
	}

	@Override
	public Optional<Engine> fetchEngine(String engineId) {
		// @formatter:off
		String sql= ""
				+ "SELECT * "
				+ "FROM engines "
				+ "WHERE engine_id = :engine_id";
				
		Map<String, Object> params = new HashMap<>();
		params.put("engine_id", engineId);
		
		// Error: Cannot return a void result
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new EngineResultSetExtractor()));
		// @formatter:on
		
	}

	@Override
	public Optional<Tire> fetchTire(String tireId) {
		
		// @formatter:off
		String sql= ""
				+ "SELECT * "
				+ "FROM tires "
				+ "WHERE tire_id = :tire_id";
				
		Map<String, Object> params = new HashMap<>();
		params.put("tire_id", tireId);
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new TireResultSetExtractor()));
		// @formatter:on
		
	}


}
	

