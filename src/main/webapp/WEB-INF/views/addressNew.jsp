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
          <form:input path="streetAddress" id="address_text" size="50" />Addr ID:
					<input type="text" name="addressId" value="" id="address_id" />
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

