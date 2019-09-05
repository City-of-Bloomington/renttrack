<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
  <fieldset>
    <legend>Rentals</legend>
		<a href="${uri}new">New Rental</a>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Status</th>
					<th scope="col">Owner(s)</th>
					<th scope="col">Agent</th>
					<th scope="col">Registered Date</th>
					<th scope="col">Last Cycle Date</th>
					<th scope="col">Permit Issue Date</th>
					<th scope="col">Permit Expires</th>
					<th scope="col">Permit Length</th>
					<th scope="col">Zoning</th>
					<th scope="col">N-Hood</th>
					<th scope="col">Special Features</th>
					<th scope="col">Active</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rentals}" var="one">
					<tr>				
						<td><a href="${uri}view/${one.id}">${one.id}</a></td>
						<td>${one.rentalStatus}</td>					
						<td>${one.ownersInfo}</td>
						<td>${one.agent}</td>					
						<td>${one.registeredDateFr}</td>
						<td>${one.lastCycleDateFr}</td>
						<td>${one.permitIssuedFr}</td>
						<td>${one.permitExpiresFr}</td>
						<td>${one.permitLength}</td>
						<td>${one.zoning}</td>
						<td>${one.NHood}</td>
						<td>${one.features}</td>
						<td>
							${empty one.inactive ? "Active":"Inactive"}
						</td>
						<td>
							<a href="${uri}delete/${one.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>

</body>
</html>
