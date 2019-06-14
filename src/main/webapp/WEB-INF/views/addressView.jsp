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
				<th>Id</th>
				<td><a href="${uri}addressEdit/${address.id}">Edit ${address.id}</a></td>
			</tr>
			<c:if test="${not empty address.rentalId}">
				<tr>
					<th>Related Rental</th>
					<td>${address.rentalId}</td>
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
