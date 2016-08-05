<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Locker Tracker</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
	<div>
		<h1>Lockers</h1>
		<table>
			<tr>
				<td>Lockers: ${lockers}.</td>
				<td><c:forEach var="entry" items="${lockers}">
					  Locker Number: <c:out value="${entry.key}" />
						<c:if test="${not empty entry.value.user.name}">Owner: <c:out
								value="${entry.value.user.name}" />
						</c:if>
					</c:forEach></td>
				<c:if test="${not empty errorMessage}">
					<td>Error message: ${errorMessage }</td>
				</c:if>
			</tr>
		</table>
		<h2>Reserve:</h2>
		<table>
			<form action="/bench/reserve" method="POST">
				<tr>
					<td>Locker id:</td>
					<td><input name="id" id="id" type="number" /></td>
					<td><form:errors path="id" class="error" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input name="name" id="name" type="text" /></td>
					<td><form:errors path="name" class="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input name="passwd" id="passwd" type="password" /></td>
					<td><form:errors path="passwd" class="error" /></td>
				</tr>
				<tr>
					<td rowspan="3"><input type="submit" value="Send" /></td>
				</tr>
			</form>
		</table>

		<h2>
			Unlock:
			</h2>
				<table>
					<form action="/bench/unlock" method="POST">
						<tr>
							<td>Locker id:</td>
							<td><input name="id" id="id" type="number" /></td>
							<td><form:errors path="id" class="error" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input name="passwd" id="passwd" type="password" /></td>
							<td><form:errors path="passwd" class="error" /></td>
						</tr>
						<tr>
							<td rowspan="3"><input type="submit" value="Send" /></td>
						</tr>
					</form>
				</table>
	</div>
</body>
</html>
