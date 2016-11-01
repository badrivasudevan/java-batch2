package com.fdm.wealthnow.backendService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.StockService;

public class OrderFetcherTask implements Runnable {

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
		
		System.out.println("Order is being submitted");

		for (Order order : orderList) {
			System.out.println("Stock (" + order.getStockSymbol() + ") is submitted.");
			workerPool.submit(new BuySellTask(order));
		}

	}

	public static void main(String[] args) {
		
		Stock stock = new Stock("Apple", "AAPL", 100.00d, 120.25d, 113.72d, "15:00");
		StockService.storeStock(stock);

		final int WORKER_THREAD_POOL_SIZE = 5;

		ScheduledExecutorService scheduledExecutorService;
		ExecutorService executorService;

		// Create worker thread pool that does stock trading
		executorService = Executors.newFixedThreadPool(WORKER_THREAD_POOL_SIZE);
		OrderFetcherTask task = new OrderFetcherTask(executorService);

		// Create a thread that wakes up periodically and scans for open orders.
		// It fetches the orders and delegates to executor service thread pool
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(task, 2, 30, TimeUnit.SECONDS);
		
		
		
	}

}
