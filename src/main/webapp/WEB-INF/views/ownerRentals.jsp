<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table class="wideTable">
	<caption>Owner Rental(s)</caption>
  <tr>
		<th>ID</th>
		<th>Status</th>		
		<th>Registered Date</th>
		<th>Cycle Date</th>
		<th>Expires</th>
		<th>Action</th>
	</tr>
  <c:forEach items="${owner.rentalOwners}" var="one">
		<tr>				
			<td><a href="${uri}view/${one.rental.id}">${one.rental.id}</a></td>
			<td>${one.rental.rentalStatus}</td>
			<td>${one.rental.registeredDateFr}</td>			
			<td>${one.rental.lastCycleDateFr}</td>
			<td>${one.rental.permitExpiresFr}</td>
			<td>
				<a href="${uri}ownerRemove/${one.id}">Remove rental</a>
			</td>
		</tr>
  </c:forEach>
</table>

