<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Edit Currency</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>


<body>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty currencies.ID}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty currencies.ID}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty currencies.ID}">
Edit User
</c:if>
						<c:if test="${empty currencies.ID}">
Add New User
</c:if>
					</h2>
				</caption>
				<c:if test="${currencies != null}">
					<input type="hidden" name="oriName"
						value="<c:out
value='${currencies.ID}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>ID</label> <input type="text"
						value="<c:out
value='${currencies.ID}' />" class="form-control"
						name="ID" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>From currency name</label> <input type="text"
						value="<c:out
value='${currencies.from_currency_name}' />" class="form-control"
						name="from_currency_name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>From currency rates</label> <input type="text"
						value="<c:out
value='${currencies.from_currency_rates}' />" class="form-control"
						name="from_currency_rates">
				</fieldset>
				<fieldset class="form-group">
					<label>To currency name</label> <input type="text"
						value="<c:out
value='${currencies.to_currency_name}' />" class="form-control"
						name="to_currency_name">
				</fieldset>
				<fieldset class="form-group">
					<label> To currency rates</label> <input type="text"
						value="<c:out
value='${currencies.to_currency_rates}' />" class="form-control"
						name="to_currency_rates">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>