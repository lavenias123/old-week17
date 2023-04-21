package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
//import com.promineotech.jeep.service.Customer;

public interface JeepOrderDao {

	Customer fetchCustomer(String customer);

	Order createOrder(OrderRequest orderRequest);

}