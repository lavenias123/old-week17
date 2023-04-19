package com.promineotech.jeep.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

//@ComponentScan
//@Component
@Service
public class DefaultJeepOrderService implements JeepOrderService {

	@Autowired
	private JeepOrderDao jeepOrderDao;
	
	
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		return jeepOrderDao.createOrder(orderRequest);
	}

}
