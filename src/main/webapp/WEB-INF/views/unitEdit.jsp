<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}unitUpdate" method="post" modelAttribute="unit">
	<form:hidden path="id" />
	<form:hidden path="rentalStructure.id" />
	<c:if test="${not empty unit.address}">
		<form:hidden path="address.id" />
	</c:if>			
  <fieldset>
    <legend>Unit</legend>
		<details>
			<summary>Instructions</summary>
			<ul>
				<li>If the unit type is efficiency mark it as efficiency checkbox, no rooms are needed</li>
				<li>Bedrooms field number will be removed and replaced by 'Unit Rooms' rows, this line is temporary for backward compatiblity only.</li>
				<li>To change the number of rooms or bedrooms add a new room or delete an existing one</li>
			</ul>
		</details>
    <table class="vertaTable">
			<tr>
				<th>ID</th>
				<td>${unit.id}</td>
			</tr>
			<tr>
					<th>Related Structure</th>
				<td><a href="${uri}structure/${unit.rentalStructure.id}">${unit.rentalStructure.id}</a></td>
				</tr>
				<c:if test="${not empty unit.address}">
					<tr>
						<th>Address</th>
						<td>${unit.address}</td>
					</tr>
				</c:if>
				<c:if test="${empty unit.address}">
					<tr>
						<th>Address</th>
						<td><input type="text" name="add_address" id="address_text" value="" />Addr ID<input type="text" name="addressId" value="" id="address_id" />
							
							<button onclick='windowOpener("${uri}addressNew","_blank", "menubar=no,toolbar=no,location=no,toolbar=no,scrollbars=no,resizable=yes,top=500,left=500,width=500,height=500");return false;'>New Address</button>											
						</td>
					</tr>
				</c:if>				
				<tr>
          <th>Identifier</th>
          <td>				
						<form:input path="identifier" size="30" /> 
            <form:errors path="identifier" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Bedrooms</th>
					<td>
            <form:input path="bedrooms" />
						(Note: this row will be removed and replaced with the next row)
					</td>					
				</tr>
				<tr>
					<th>Rooms - Bedrooms</th>
					<td>${unit.roomCount} - ${unit.bedroomCount}</td>
				</tr>							
				<tr>
          <th>Occ Load</th>
					<td>
            <form:input path="occupLoad" />
            <form:errors path="occupLoad" cssClass="error" />							
					</td>					
				</tr>
				<tr>
          <th>Bathrooms</th>
					<td>
            <form:input path="bathrooms" />
						<form:errors path="bathrooms" cssClass="error" />	
					</td>					
				</tr>				
				<tr>
          <th>Half Bath(s)</th>
					<td>
            <form:input path="halfBath" />
						<form:errors path="halfBath" cssClass="error" />	
					</td>					
				</tr>
				<tr>
          <th>Sleeping Room?</th>
					<td>
            <form:checkbox path="sleepRoom" value="y" />
					</td>					
				</tr>
				<tr>
          <th>Efficiency?</th>
					<td>
            <form:checkbox path="efficiency" value="y" /> 
					</td>			
				</tr>							
				<tr>
          <th>Accessory Dwelling?</th>
					<td>
            <form:checkbox path="accessoryDwelling" value="y" />
					</td>					
				</tr>
				<tr>
          <th>Attic Access?</th>
					<td>
            <form:checkbox path="atticAccess" value="y" />
					</td>					
				</tr>				
				<tr>
          <th>Unispected?</th>
					<td>
            <form:checkbox path="uninspected" value="y" />
					</td>					
				</tr>
				<tr>
          <th>Notes</th>
					<td>
            <form:textarea path="notes" rows="5" cols="50" /> 
            <form:errors path="notes" cssClass="error" />
					</td>			
				</tr>
			</table>
	</fieldset>
	<fieldset>
		<table class="vertaTable">
			<tr>
        <td>
					<button type="submit">Save Changes</button>
				</td>
				<c:if test="${empty unit.address}">
					<td>
						<input type="button" value="Add Address" onclick="window.location='${uri}addressNew/unit/${unit.id}'" />
					</td>
				</c:if>
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
  <br />
</form:form>
<jsp:include page="footer.jsp" />		
	

