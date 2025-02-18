<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculate Page</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


</head>
<body>
	<form action="calculate" method="post">
		<div class="container col-md-6">
			<div class="card">
				<div class="card-body">
					<h1>Reference of selected rates</h1>
					
				
					<fieldset class="form-group">
						<label>From currency name</label> <input type="text"
							value="${currencies.from_currency_name}"
							class="form-control" name="from_currency_name"
							required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>From currency rates</label> <input type="text"
							value="${currencies.from_currency_rates}"
							class="form-control" name="from_currency_rates">
					</fieldset>
					<fieldset class="form-group">
						<label>To currency name</label> <input type="text"
							value="${currencies.to_currency_name}"
							class="form-control" name="to_currency_name">
					</fieldset>
					<fieldset class="form-group">
						<label> To currency rates</label> <input type="text"
							value="${currencies.to_currency_rates}"
							class="form-control" name="to_currency_rates">
					</fieldset>
				</div>
			</div>
		</div>

		<br>

		<div class="container col-md-6">
			<div class="card">
				<div class="card-body">

					<h1>Calculator</h1>
					<table>
						<tr>
							<td>Amount</td>
						</tr>

						<tr>
							<td><input type="text" name="calculateAmount"></td>
						</tr>

						<tr>
							<td><input type="submit" value="Calculate" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>