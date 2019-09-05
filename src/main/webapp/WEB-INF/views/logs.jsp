<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table>
	<caption>Update History</caption>
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Date</th>
			<th scope="col">By</th>
			<th scope="col">Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rentalLogs}" var="one" varStatus="row">
			<c:if test="${row.index < 5}">
				<tr>
					<td>${one.id}</td>
					<td>${one.dateFr}</td>
				<td><c:if test="${not empty one.user}">${one.user}</c:if></td>
				<td>${one.actionTaken}</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>

