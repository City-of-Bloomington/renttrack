<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Units</legend>
	<table>
		<thead>
			<tr>
				<th width="5%" scope="col">ID</th>
				<th width="5%" scope="col">Identifier</th>
				<th width="5%" scope="col">Rooms - Bedrooms</th>
				<th width="10%" scope="col">Occup load</th>
				<th width="5%" scope="col">Bathrooms</th>
				<th width="35%" scope="col">Other Info</th>				
				<th width="35%" scope="col">Address</th>
			</tr>
		</thead>
		<tbody>
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
		</tbody>
	</table>
</fieldset>	
