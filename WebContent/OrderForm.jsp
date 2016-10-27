<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#PriceTypeBuy').on('change', function() { //# is ID
            if (this.value == 'Limit') {
                $("#Limit-Buy").show(); //# is div
            } else {
                $("#Limit-Buy").hide();
            }
        });

        $('#PriceTypeSell').on('change', function() { //# is ID
            if (this.value == 'Limit') {
                $("#Limit-Sell").show(); //# is div
            } else {
                $("#Limit-Sell").hide();
            }

        });

        $('#PriceTypeSell').on('change', function() { //# is ID
            if (this.value == 'Stoploss') {
                $("#Stop-loss").show(); //# is div
            } else {
                $("#Stop-loss").hide();
            }
        });
    });
    </script>
    <script type="text/javascript">
    window.onload = function() {
        document.getElementById('buyChecked').style.display = 'none';
        document.getElementById('sellChecked').style.display = 'none';

    }

    function OrderTypeCheck() {
        if (document.getElementById('transactionTypeBuy').checked) {
            document.getElementById('buyChecked').style.display = 'block';
        } else {
            document.getElementById('buyChecked').style.display = 'none';
        }

        if (document.getElementById('transactionTypeSell').checked) {
            document.getElementById('sellChecked').style.display = 'block';
        } else {
            document.getElementById('sellChecked').style.display = 'none';
        }

    }

    </script>
    <!--     <link rel="stylesheet" type="text/css" href="./css/testpage1.css">-->
    
</head>

<body>
    <h3>Stock Order Form</h3>
    <form>
        <label for="stock">Stock</label>
        <select name="stock">
            <option selected="true" disabled="disabled">Choose stock</option>
            <option value="Apple">AAPL</option>
            <option value="Google">GOOGL</option>
            <option value="Microsoft">MSFT</option>
        </select>
        <p>
            <input type="radio" name="transactionType" value="Buy" id=transactionTypeBuy onclick="javascript:OrderTypeCheck();">
            <label for="transactionTypeBuy">Buy</label>
            <input type="radio" name="transactionType" value="Sell" id=transactionTypeSell onclick="javascript:OrderTypeCheck();">
            <label for="transactionTypeSell">Sell</label>
        </p>
        <div id="buyChecked">
            <select id="PriceTypeBuy" type="list" name="PriceType">
                <option value="Marketprice"> Market Price </option>
                <option value="Limit" id="limit"> Limit </option>
            </select>
        </div>
        <div id="sellChecked">
            <select id="PriceTypeSell" type="list" name="PriceType">
                <option value="Marketprice"> Market Price </option>
                <option value="Limit" id="limit"> Limit </option>
                <option value="Stoploss" id="stoploss"> Stop Loss </option>
            </select>
        </div>
        <div id="Limit-Buy" style='display:none;'>
            <label for="limitBuy">Buy limit</label>
            <input type="text" id="limitBuy" name="LimitBuy">
        </div>
        <div id="Limit-Sell" style='display:none;'>
            <label for="limitSell">Sell limit</label>
            <input type="text" id="limitSell" name="LimitSell">
        </div>
        <div id="Stop-loss" style='display:none;'>
            <label for="Stop-loss">Stop Loss</label>
            <input type="text" id="stopLoss" name="StopLoss">
        </div>
        <label for="quantity">Quantity</label>
        <input id="quantity" type="text" style="width: 50px;" name=Quantity/>
        <p>
            <label for="Term">Term</label>
            <select name="Term">
                <option value="GoodTilCanceled"> Good 'Til Canceled </option>
                <option value="GoodTilDay"> Good 'Til Day </option>
            </select>
        </p>
        <input type="submit" value="Submit" />
    </form>
</body>

</html>