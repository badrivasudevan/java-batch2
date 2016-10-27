package com.fdm.wealthnow.common;

public class WatchlistService {

	public void createNewWatchlist(int user_id, String watchlistname) {
		//update watchlist and userswatchlist table
	}	
	
	public void editWatchlistName(String newname, String w_id) {
		// set watchlist_name in watchlist table = newname where w_id=w_id
	}
	
	public void selectWatchlist(int user_id, String w_id) {
		// show the content of watchlist in that particular user id
	}
	
	public void removeWatchlist(int user_id, String w_id) {
		// delete from watchlist table where w_id = w_id
		// delete from userswatchlist table where user_id=user_id and w_id = w_id
	}
	
	
	
	
}
