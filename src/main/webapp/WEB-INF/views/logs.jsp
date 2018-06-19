<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table class="outTable">
	<caption>Update History</caption>
  <tr>
		<th>ID</th>
		<th>Date</th>
		<th>By</th>
		<th>Action</th>
	</tr>
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
</table>

