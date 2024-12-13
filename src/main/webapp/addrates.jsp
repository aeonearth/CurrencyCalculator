<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Rates Page</title>
</head>
<body>

	<form action="AddCurrencyRates" method="Post">
	
	<table>
	<tr>
	<td>From currency name</td>
	<td><select name="fromCurrencyName">
						<option>SGD</option>
						<option>USD</option>
						<option>MYR</option>
						<option>JPY</option>
						<option>RMB</option>
	</select></td>
	</tr>
	
	<tr>
	<td>From currency rates</td>
	<td><input type="text" name="fromCurrencyRates"></td>
	</tr>
	
	<tr>
	<td>To currency name</td>
	<td><select name="toCurrencyName">
						<option>SGD</option>
						<option>USD</option>
						<option>MYR</option>
						<option>JPY</option>
						<option>RMB</option>
				</select></td>
	</tr>
	
	<tr>
	<td>To currency rates</td>
	<td><input type="text" name="toCurrencyRates"></td>
	</tr>
	</table>
	
    <input type="submit" value="Add Currency Rates" />
	</form>

</body>
</html>