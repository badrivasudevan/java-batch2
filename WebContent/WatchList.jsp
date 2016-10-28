<html>
<head>
	<style>
	
	</style>
</head>
<body>
	<h2>Watchlist</h2>
	<p>Current WatchList: ${watchlist}</p>
	<p>Current WatchList Name: ${WatchListName} </p>
	
	<div id = "watchlist">
	<label><Strong>WatchList</Strong></label> <br>
	Watchlist Name: <input type = "text" name = "WatchListName"> <br>
	<input type = "button" value = "Add WatchList" onclick = ""> 
	<input type = "button" value = "Remove WatchList" onclick = ""> <br> <br>

	Stocks: <input type = "text" name = "stockname" value = ""> 
	<input type = "button" value = "Add to Current WatchList" onclick = ""> 
	<input type = "button" value = "Remove from Current WatchList" onclick=""> <br>

	<select>
			<option value = "Select WatchLists"> Select WatchLists </option>
	</select>

	<select>
			<option value = "WatchLists Type"> Type </option>
			<option value = "WatchLists Type"> Global </option>
			<option value = "WatchLists Type"> Private </option>
	</select>
	</div> <br>

	<div id = "righttab">
		<select>
			<option value = "WatchLists"> WatchLists </option>
		</select>
	</div>

	<div id = "watchlistmain">
	</div>
</body>
</html>