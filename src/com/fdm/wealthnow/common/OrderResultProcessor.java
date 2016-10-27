package com.fdm.wealthnow.common;

import src.com.fdm.wealthnow.common.Logger;
import src.com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;

public class OrderResultProcessor {
	private final Logger logger;

	public OrderResultProcessor(Logger logger){
		this.logger = logger;
	}

	public void update(Order order){

		if(order.getStatus() == OrderStatus.Processed){
			logger.log(order.orderId + " is processed");
		}
		if(order.getStatus() == OrderStatus.Pending){
			logger.log(order.orderId + " is pending");
		}
		logger.log(order.orderId + " is unsuccessful");

	}

}
