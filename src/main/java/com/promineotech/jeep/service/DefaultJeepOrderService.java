package com.promineotech.jeep.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import from which pkg 
import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.Color;
//import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import com.promineotech.jeep.entity.Tire;


@Service
public class DefaultJeepOrderService implements JeepOrderService {

	@Autowired
	private JeepOrderDao jeepOrderDao;
	
	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		// fetch all records
		Customer customer = jeepOrderDao.fetchCustomer(orderRequest.getCustomer());
		
		Jeep jeep = jeepOrderDao
				.fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors());
//				.fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors());
		
		Color color = jeepOrderDao
				.fetchColor(orderRequest.getColor());
		
		Engine engine = jeepOrderDao
		.fetchEngine(orderRequest.getEngine());
		
		Tire tire = jeepOrderDao
				.fetchTire(orderRequest.getTire());
		
		
		return null;
//		return jeepOrderDao.createOrder(orderRequest);
	}

}
