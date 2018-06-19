<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
  <fieldset>
    <legend>Unit</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th>
				<td><a href="${uri}unitEdit/${unit.id}">Edit ${unit.id}</a></td>
			</tr>
			<tr>
				<th>Related Building</th>
				<td><a href="${uri}structure/${unit.rentalStructure.id}">${unit.rentalStructure.id}</a></td>
			</tr>
			<tr>
				<th>Identifier</th>
        <td>${unit.identifier}</td>
			</tr>
			<tr>
				<th>Address</th>
        <td><c:if test="${not empty unit.address}">${unit.address}</c:if></td>
			</tr>			
			<tr>
				<th>Bedrooms</th>
        <td>${unit.bedrooms}
						(Note: this row will be removed and replaced with the next row)
				</td>
			</tr>
			<tr>
				<th>Rooms - Bedrooms</th>
        <td>${unit.roomCount} - ${unit.bedroomCount}</td>
			</tr>			
			<tr>
				<th>Occup Load</th>
        <td>${unit.occupLoad}</td>
			</tr>
			<tr>
				<th>Bathrooms</th>
        <td>${unit.bathrooms}</td>
			</tr>			
			<tr>
				<th>Other Info</th>
        <td>${unit.features}</td>
			</tr>
			<tr>
				<th>Notes</th>
        <td>${unit.notes}</td>
			</tr>
    </table>
  </fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
				<td>
					<input type="button" value="Edit" onclick="window.location='${uri}unitEdit/${unit.id}'" />
				</td>
				<td>
					<input type="button" value="Add New Room" onclick="window.location='${uri}roomNew/${unit.id}'" />
				</td>				
				<td>
					<input type="button" value="New Unit" onclick="window.location='${uri}unitNew/${unit.rentalStructure.id}'" />
				</td>
				<td>
					<input type="button" value="Delete" onclick="window.location='${uri}unitDelete/${unit.id}'" />
				</td>
      </tr>
		</table>
	</fieldset>
	<c:if test="${unit.hasRooms()}">
		<fieldset>
			<legend>Unit Rooms</legend>
			<jsp:include page="rooms.jsp" />
		</fieldset>
	</c:if>
</body>
</html>
