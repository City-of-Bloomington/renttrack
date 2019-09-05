<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="header.jsp" />
<form:form action="${uri}addressUpdate" method="post" modelAttribute="address">
	<form:hidden path="id" />
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>			
	<c:if test="${not empty address.rentalId}">
		<form:hidden path="rentalId" />
	</c:if>
	<c:if test="${not empty address.unitId}">
		<form:hidden path="unitId" />
	</c:if>	
  <fieldset>
    <legend>Address</legend>
			<div class="form-group">
				<fieldset>
					<div class="field-group">
						<label>ID: </label>
						${address.id}
					</div>
					<c:if test="${not empty address.unitId}">
						<div class="field-group">
							<label>Related Unit</label>
							<a href="${uri}unit/${address.unitId}">Back to ${address.unitId}</a>			
						</div>
					</c:if>			
					<c:if test="${not empty address.rentalId}">
						<div class="field-group">				
							<label>Related Rental</label>
							<a href="${uri}view/${address.rentalId}" class="button">Back to ${address.rentalId}</a>					
						</div>
					</c:if>			
					<div class="field-group">						
						<label for="addr_id">Street Address</label>
						<form:input path="streetAddress" id="addr_id" size="30" />
						<button  id="address_id-chooserButton" type="button" class="choose"
							onclick="ADDRESS_CHOOSER.start(handleChoice, {addressQuery:document.getElementById('addr_id').value})">Look up address</button>
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
						<label for="city">City</label>
						<form:input path="city" size="30" id="city" /> 
					</div>
					<div class="field-group">		
						<label for="state">State</label>
						<form:input path="state" size="2" id="state" /> 
					</div>
					<div class="field-group">		
						<label for="zip">Zip</label>
						<form:input path="zip" size="10" id="zip" /> 
					</div>
					<c:if test="${address.isInvalidAddr()}">
						<div class="field-group">
							<label>&nbsp;</label>
							Invalid Address
						</div>
					</c:if>
				</fieldset>
			</div>
	</fieldset>
	<fieldset>
		<div>
			<div>
				<button type="submit">Save Changes</button>
				<input type="button" value="Delete" onclick="window.location='${uri}addressDelete/${address.id}'" class="button" />		
			</div>
		</div>
	</fieldset>
</form:form>
<br />
<jsp:include page="footer.jsp" />

