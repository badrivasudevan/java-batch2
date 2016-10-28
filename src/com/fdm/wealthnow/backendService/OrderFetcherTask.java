package com.fdm.wealthnow.backendService;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.fdm.wealthnow.common.Order;

public class OrderFetcherTask implements Runnable{
	
	ExecutorService workerPool;

	public OrderFetcherTask(ExecutorService workerPool) {
		this.workerPool = workerPool;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Order> orderList = null;
		
		try {
			orderList = OrderService.getPendingOrders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Order order : orderList){
			workerPool.submit(new BuySellTask(order));
		}
		
	}

}
