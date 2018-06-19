<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="header.jsp" />
<form:form action="${uri}addressUpdate" method="post" modelAttribute="address">
	<form:hidden path="id" />
	<c:if test="${address.hasLatLong()}">
		<form:hidden path="latitude" />
		<form:hidden path="longitude" />
	</c:if>
	<c:if test="${not empty address.rentalId}">
		<form:hidden path="rentalId" />
	</c:if>
	<c:if test="${not empty address.maLocationId}">
		<form:hidden path="maLocationId" />
	</c:if>	
	<c:if test="${not empty address.maStreetId}">
		<form:hidden path="maStreetId" />
	</c:if>
	<c:if test="${not empty address.maSubunitId}">
		<form:hidden path="maSubunitId" />
	</c:if>
	<c:if test="${address.isInvalidAddr()}">
		<form:hidden path="invalid" />
	</c:if>
  <fieldset>
    <legend>Address</legend>
    <table class="vertaTable">
			<tr>
				<th>ID</th>
				<td>${address.id}</td>
			</tr>
			<c:if test="${not empty address.rentalId}">				
				<tr>
					<th>Related Rental</th>
					<td>${address.rentalId}</td>
				</tr>
			</c:if>
			<tr>
        <th>Street address</th>
        <td>				
					<form:input path="streetAddress" size="30" /> 
          <form:errors path="streetAddress" cssClass="error" />
        </td>
			</tr>
			<tr>
        <th>City</th>
        <td>
          <form:input path="city" size="30" /> 
          <form:errors path="city" cssClass="error" />
				</td>
			</tr>
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
					<button type="submit">Save Changes</button>
				</td>
				<td>
					<input type="button" value="Delete" onclick="window.location='${uri}addressDelete/${address.id}'" />		
				</td>
      </tr>
    </table>
	</fieldset>
</form:form>
<br />

</body>
</html>
