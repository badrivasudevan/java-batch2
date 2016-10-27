package src.com.fdm.wealthnow.common;

import java.util.ArrayList;
import java.util.List;

public class WatchList {
	private int userId;
	private String watchlist_name;
	private List<Stock> watchlist = new ArrayList<>();
	
	public WatchList(int userId, String watchlist_name){
		this.userId=userId;
		this.watchlist_name=watchlist_name;
	}
	
	
	//Store the list for that particular userid in SQL database by calling SQL statements in java
	
}