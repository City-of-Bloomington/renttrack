<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>		
  <fieldset>
    <legend>Address</legend>
    <table class="vertaTable">
      <tr>
				<th>ID</th>
				<td><a href="${uri}addressEdit/${address.id}">Edit ${address.id}</a></td>
			</tr>
			<c:if test="${not empty address.unitId}">
				<tr>
					<th>Related Unit</th>
					<td><a href="${uri}unit/${address.unitId}">Back to ${address.unitId}</a></td>					
				</tr>
			</c:if>			
			<c:if test="${not empty address.rentalId}">
				<tr>
					<th>Related Rental</th>
					<td><a href="${uri}view/${address.rentalId}">Back to ${address.rentalId}</a></td>					
				</tr>
			</c:if>
			<tr>
				<th>Street Address</th>
        <td>${address.streetAddress}</td>
			</tr>
			<tr>
				<th>City</th>
				<td>${address.city}</td>
			</tr>
			<tr>
				<th>State</th>
				<td>${address.state}</td>
			</tr>
			<tr>
				<th>Zip Code</th>
				<td>${address.zip}</td>
			</tr>
			<c:if test="${address.hasLatLong()}">			
				<tr>
					<th>Lat, Long</th>
					<td>${address.latitude}, ${address.longitude} </td>
				</tr>
			</c:if>
			<c:if test="${address.hasMasterAddressInfo()}">			
				<tr>
					<th>Related Master Address:</th>
					<td>${address.masterAddressInfo} </td>
				</tr>
			</c:if>			
			<c:if test="${address.isInvalidAddr()}">
				<tr>
					<td colspan="2">Invalid Address</td>
				</tr>
			</c:if>
		</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
				<td>
					<input type="button" value="Delete" onclick="window.location='${uri}addressDelete/${address.id}'" />
				</td>
      </tr>
    </table>
  </fieldset>
</body>
</html>
