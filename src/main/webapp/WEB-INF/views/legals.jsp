<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table class="outTable">
	<caption>Rental Legals</caption>			
  <tr>
		<th>Legal ID</th>				
		<th>Date</th>
		<th>Initiated By</th>
	</tr>
  <c:forEach items="${rental.rentalLegals}" var="legal">
		<tr>				
			<td>${legal.id}</td>
			<td>${legal.dateFr}</td>
			<td>${legal.startByUser}</td>
		</tr>
  </c:forEach>
</table>


