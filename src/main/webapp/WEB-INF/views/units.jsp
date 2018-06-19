<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Units</legend>
	<table class="wideTable">
		<tr>
			<th width="5%">ID</th>
			<th width="5%">Identifier</th>
			<th width="5%">Rooms - Bedrooms</th>
			<th width="10%">Occup load</th>
			<th width="5%">Bathrooms</th>
			<th width="35%">Other Info</th>				
			<th width="35%">Address</th>
		</tr>
		<c:forEach items="${structure.rentalUnits}" var="unit">
			<tr>				
				<td><a href="${uri}unit/${unit.id}">${unit.id}</a></td>
				<td>${unit.identifier}</td>
				<td>${unit.roomCount} - ${unit.bedroomCount}</td>				
				<td>${unit.occupLoad}</td>
				<td>${unit.bathrooms}</td>
				<td>${unit.features}</td>					
				<td><c:if test="${not empty unit.address}">${unit.address}</c:if></td>
			</tr>
		</c:forEach>
	</table>
</fieldset>	
