<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<caption>Owner(s)</caption>
	<thead>
		<tr>
			<th scope="col">Name</th>
			<th scope="col">Address</th>
			<th scope="col">City, State Zip</th>
			<th scope="col">Email</th>
			<th scope="col">Phones</th>
			<th scope="col">Action</th>
		</tr>
	</thead>
	<tbody>
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
	</tbody>
</table>

