<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="wideTable">
	<caption>Owner(s)</caption>
  <tr>
		<th>Name</th>
		<th>Address</th>
		<th>City, State Zip</th>
		<th>Email</th>
		<th>Phones</th>
		<th>Action</th>
	</tr>
  <c:forEach items="${rental.rentalOwners}" var="one">
		<tr>				
			<td><a href="${uri}owner/${one.owner.id}">${one.owner.name}</a></td>
			<td>${one.owner.address}</td>
			<td>${one.owner.cityStateZip}</td>
			<td>${one.owner.email}</td>
			<td>${one.owner.phonesInfo}</td>
			<td>
				<a href="${uri}ownerRemove/${one.id}">Remove from rental</a>
			</td>
		</tr>
  </c:forEach>
</table>

