<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
<table>
	<caption>Bills</caption>
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Issue Date</th>
			<th scope="col">Due Date</th>
			<th scope="col">Total</th>
			<th scope="col">Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rental.bills}" var="bill">
			<tr>				
				<td><a href="${uri}billView/${bill.id}">${bill.id}</a></td>
				<td>${bill.issueDateFr}</td>					
				<td>${bill.dueDateFr}</td>
				<td>${bill.total}</td>
				<td>${bill.status}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>
