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
			<div class="form-group">
				<fieldset>
					<div class="field-group">
						<label>ID: </label>
						<a href="${uri}addressEdit/${address.id}">Edit ${address.id}</a></td>
					</div>
					<c:if test="${not empty address.unitId}">
						<div class="field-group">						
							<label>Related Unit: </label>
							<a href="${uri}unit/${address.unitId}">Back to ${address.unitId}</a></td>					
						</div>
					</c:if>			
					<c:if test="${not empty address.rentalId}">
						<div class="field-group">			
							<label>Related Rental: </label>
							<a href="${uri}view/${address.rentalId}">Back to ${address.rentalId}</a></td>					
						</div>
					</c:if>
					<div class="field-group">			
						<label>Street Address: </label>
						${address.streetAddress}
					</div>
					<div class="field-group">			
						<label>City: </label>
						${address.city}
					</div>
					<div class="field-group">		
						<label>State: </label>
						${address.state}
					</div>
					<div class="field-group">		
						<label>Zip Code: </label>
						${address.zip}
					</div>				
					<c:if test="${address.hasLatLong()}">			
						<div class="field-group">		
							<label>Lat, Long: </label>
							${address.latitude}, ${address.longitude} 
						</div>
					</c:if>
					<c:if test="${address.hasMasterAddressInfo()}">			
						<div class="field-group">	
							<label>Related Master Address:</label> 
							${address.masterAddressInfo}
						</div>
					</c:if>			
					<c:if test="${address.isInvalidAddr()}">
						<div class="field-group">	
							<label>Note:</label> 
							Invalid Address
						</div>
					</c:if>
				</fieldset>
			</div>
			<fieldset>
				<div>
					<div>
						<input type="button" value="Delete" onclick="window.location='${uri}addressDelete/${address.id}'" />
					</div>
				</div>
			</fieldset>
	</fieldset>
</body>
</html>
