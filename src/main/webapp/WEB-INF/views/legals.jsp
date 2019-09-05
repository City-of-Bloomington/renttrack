<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table>
	<caption>Rental Legals</caption>
	<thead>
		<tr>
			<th scope="col">Legal ID</th>				
			<th scope="col">Date</th>
			<th scope="col">Initiated By</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rental.rentalLegals}" var="legal">
			<tr>				
				<td>${legal.id}</td>
				<td>${legal.dateFr}</td>
				<td>${legal.startByUser}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


