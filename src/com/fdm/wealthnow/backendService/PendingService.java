package com.fdm.wealthnow.backendService;
import java.util.List;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.UserDAO;

public class PendingService {
	
	
	
	public static List<Order> callDAO(long userId) throws Exception{
		List<Order> pendingList= OrderDAO.fetchPending(userId); 
		return pendingList;

	}
}
