<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}addressSave" method="post" modelAttribute="address">
	<c:if test="${not empty address.rentalId}">
		<form:hidden path="rentalId" />
	</c:if>
	<c:if test="${not empty address.unitId}">
		<form:hidden path="unitId" />
	</c:if>
	<fieldset>
    <legend>New Address</legend>
		<div class="form-group">
			<fieldset>
				<c:if test="${not empty address.rentalId}">
					<c:if test="${not empty address.unitId}">				
						<div class="field-group">		
							<label>Related Unit </label>
							<a href="${uri}unit/${address.unitId}">Back to ${address.unitId}</a></td>					
						</div>
					</c:if>
					<div class="field-group">		
						<label>Related Rental: </label>
						<a href="${uri}view/${address.rentalId}">Back to ${address.rentalId}</a></td>							
					</div>
				</c:if>
				<div class="field-group">		
					<label>Street Address</label>
					<form:input path="streetAddress" id="addr_id" size="30" />
					<button  id="address_id-chooserButton" type="button" class="choose"
						onclick="ADDRESS_CHOOSER.start(handleChoice, {addressQuery:document.getElementById('addr_id').value})" class="button">Look up address</button>
				</div>
				<div class="field-group">							
					<label>Master Address Id </label>
					<form:input path="maAddressId" id="address_id" readonly="true"/>
				</div>
				<div class="field-group">		
					<label>location Id </label>
					<form:input path="maLocationId" id="location_id" readonly="true" />
				</div>
				<div class="field-group">		
					<label>Subunit Id</label>
					<form:input path="maSubunitId" id="subunit_id" readonly="true" />
				</div>
				<div class="field-group">		
					<label>Latitude </label>
					<form:input path="latitude" id="latitude" readonly="true" />
				</div>
				<div class="field-group">		
					<label>Longitude</label>
					<form:input path="longitude" id="longitude" readonly="true" />
				</div>
				<div class="field-group">		
					<label>City</label>
					<form:input path="city" size="30" id="city" /> 
				</div>
				<div class="field-group">		
					<label>State</label>
					<form:input path="state" size="2" id="state" /> 
				</div>
				<div class="field-group">		
					<label>Zip</label> 
					<form:input path="zip" size="10" id="zip" /> 
				</div>
			</fieldset>
		</div>
		<fieldset>
			<div>
				<div>
					<button type="submit">Save</button>
				</div>
			</div>
		</fieldset>
  </form:form>
<jsp:include page="footer.jsp" />


