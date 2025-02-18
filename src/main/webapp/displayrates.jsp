<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Rate Page 2024</title>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

 
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Currency Rates 2024</h3>
			<hr>

			<table>
				<tr>
					<td>
						<div class="container text-left">
							<a href="<%=request.getContextPath()%>/addrates.jsp"
								class="btn btn-success">Add New Rates</a>
						</div>
					</td>
				</tr>
			</table>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>From Currency Name</th>
						<th>From Currency Rates</th>
						<th>To Currency Name</th>
						<th>To Currency Rates</th>
						<th>Last Modified Datetime</th>
					</tr>
				</thead>
				<!-- Pass in the list of data receive via the Servlet response to a loop -->
				<tbody>
					<c:forEach var="currencies" items="${listCurrencies}">
						<!-- For each user in the database, display their information accordingly -->

						<tr>

							<td><c:out value="${currencies.ID}">0</c:out></td>
							<td><c:out value="${currencies.from_currency_name}">0</c:out></td>
							<td><c:out value="${currencies.from_currency_rates}">0</c:out></td>
							<td><c:out value="${currencies.to_currency_name}">0</c:out></td>
							<td><c:out value="${currencies.to_currency_rates}">0</c:out></td>
							<td><c:out value="${currencies.last_modified_date}">0</c:out></td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a href="calculator?ID=<c:out value='${currencies.ID}' />">Calculate</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="edit?ID=<c:out value='${currencies.ID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?ID=<c:out value='${currencies.ID}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>