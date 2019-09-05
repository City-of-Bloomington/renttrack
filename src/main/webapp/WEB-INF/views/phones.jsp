<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table>
	<caption>Phones</caption>
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Phone #</th>
			<th scope="col">Type</th>
			<th scope="col">Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${phones}" var="phone" varStatus="row">
			<tr>				
				<td>${phone.id}</td>
				<td>${phone.phoneNum}</td>
				<td>${phone.type}</td>
				<td>
					<a href="${uri}phoneDelete/${phone.id}">Delete</a> <br />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

