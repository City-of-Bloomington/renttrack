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
    <table class="wideTable">
      <tr>
				<th>ID</th>
				<th>Status</th>
				<th>Owner(s)</th>
				<th>Agent</th>
				<th>Registered Date</th>
				<th>Last Cycle Date</th>
				
				<th>Permit Issue Date</th>
				<th>Permit Expires</th>
				<th>Permit Length</th>
				<th>Zoning</th>
				<th>N-Hood</th>
				<th>Special Features</th>
				<th>Active</th>
				<th>Action</th>
			</tr>
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
    </table>
  </fieldset>

</body>
</html>
