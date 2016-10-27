package com.fdm.wealthnow.listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
	private static final int WORKER_THREAD_POOL_SIZE = 5;
	
	private ScheduledExecutorService scheduledExecutorService;
	private ExecutorService executorService;
	
    public ContextListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("***** ContextListener shutting down *****");
         scheduledExecutorService.shutdown();
         executorService.shutdown();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    		System.out.println("***** ContextListener starting up *****");
    		initialize();
    }
    
    private void initialize() {
    			// Create a thread that wakes up periodically and scans for open orders.
    			// It fetches the orders and delegates to executor service thread pool
    			scheduledExecutorService = Executors.newScheduledThreadPool(1);
    			//scheduledExecutorService.scheduleAtFixedRate(runnable, 2, 2, 30, TimeUnit.SECONDS);
    			
    			// Create worker thread pool that does stock trading
    			executorService = Executors.newFixedThreadPool(WORKER_THREAD_POOL_SIZE);
    }
	
}
