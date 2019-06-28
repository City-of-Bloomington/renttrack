<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}addressSave" method="post" modelAttribute="address">
		<c:if test="${not empty address.rentalId}">
			<form:hidden path="rentalId" />
		</c:if>
		<c:if test="${not empty address.unitId}">
			<form:hidden path="unitId" />
		</c:if>
		<fieldset>
    <legend>New Address</legend>
    <table class="vertaTable">
			<c:if test="${not empty address.rentalId}">				
				<tr>
					<th>Related Rental</th>
					<td>${address.rentalId}</td>
				</tr>
			</c:if>
			<c:if test="${not empty address.unitId}">				
				<tr>
					<th>Related Unit</th>
					<td>${address.unitId}</td>
				</tr>
			</c:if>
			<tr>
        <th>Street Address</th>
        <td>
          <form:input path="streetAddress" id="addr_id" size="30" />
					<button  id="address_id-chooserButton" type="button" class="choose"
            onclick="ADDRESS_CHOOSER.start(handleChoice)">Look up address</button>
				</td>
			</tr>
			<tr>
				<th>Master Address Id </th>
				<td><form:input path="addressId" id="address_id" disabled="true" />
				</td>
			</tr>
			<tr>
				<th>location Id </th>
				<td>
					<form:input path="maLocationId" id="location_id" disabled="true" />
				</td>
			</tr>
			<tr>
				<th>Subunit Id</th>
				<td>
					<form:input path="maSubunitId" id="subunit_id" disabled="true" />
				</td>
			</tr>
			<tr>
				<th>Lat </th>
				<td>
					<form:input path="latitude" id="latitude" disabled="true" />
				</td>
			</tr>
			<tr>
				<th>Long</th>
				<td>
					<form:input path="longitude" id="longitude" disabled="true" />
				</td>
			</tr>
			<tr>
				<th>City</th>
				<td>
					<form:input path="city" size="30" id="city" /> 
				</td>
			</tr>
			<tr>
        <th>State</th>
        <td>
          <form:input path="state" size="2" id="state" /> 
				</td>
			</tr>
			<tr>
        <th>Zip</th>
        <td>
          <form:input path="zip" size="10" id="zip" /> 
				</td>
			</tr>			
		</table>
		</fieldset>
		<fieldset>
			<table class="submitTable">
				<tr>
					<td><button type="submit">Save</button></td>
				</tr>
      </table>
  </fieldset>			
  </form:form>
<jsp:include page="footer.jsp" />


