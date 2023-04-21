package com.promineotech.jeep.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import from which pkg 
import com.promineotech.jeep.dao.JeepOrderDao;
//import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;


@Service
public class DefaultJeepOrderService implements JeepOrderService {

	@Autowired
	private JeepOrderDao jeepOrderDao;
	
	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		// fetch all records
		Customer customer = jeepOrderDao.fetchCustomer(orderRequest.getCustomer());
		
		return null;
//		return jeepOrderDao.createOrder(orderRequest);
	}

}
