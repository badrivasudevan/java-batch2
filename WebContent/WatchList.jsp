<html>
<head>
	<style>
	
	</style>
</head>
<body>
	<h2>Watchlist</h2>
	
	<fieldset>
		Current WatchList: ${watchlist} <br>
		Current WatchList Name: ${WatchListName} 
	</fieldset>
	
	<fieldset>
		<legend>Watchlist Tools Bar</legend>
			<div id = "watchlist">
			Watchlist Name: <input type = "text" name = "WatchListName"> <br>
			<input type = "button" value = "Add WatchList" onclick = ""> 
			<input type = "button" value = "Remove WatchList" onclick = ""> <br> <br>
		
			Stocks: <input type = "text" name = "stockname" value = ""> 
			<input type = "button" value = "Add to Current WatchList" onclick = ""> 
			<input type = "button" value = "Remove from Current WatchList" onclick=""> <br><br>
		
			<select>
					<option value = "Select WatchLists"> Select WatchLists </option>
			</select>
		
			<select>
					<option value = "WatchLists Type"> Type </option>
					<option value = "WatchLists Type"> Global </option>
					<option value = "WatchLists Type"> Private </option>
			</select>
			</div> <br>
	</fieldset>
	
	<fieldset>
		<legend>Watchlist</legend>
			<div id = "righttab">
				<select>
					<option value = "WatchLists"> WatchLists </option>
				</select>
			</div>
		
			<div id = "watchlistmain">
			</div>
	</fieldset>
</body>
</html>