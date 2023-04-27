package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
//import com.promineotech.jeep.service.Customer;
import com.promineotech.jeep.entity.Tire;

public interface JeepOrderDao {

	Customer fetchCustomer(String customer);

	Order createOrder(OrderRequest orderRequest);

//	Jeep fetchModel(String model);

	Color fetchColor(String color);

	Engine fetchEngine(String engine);

	Tire fetchTire(String tire);

	Jeep fetchModel(JeepModel model, String trim, int doors);

}