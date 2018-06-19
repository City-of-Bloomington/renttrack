<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}unitSave" method="post" modelAttribute="unit">
		<form:hidden path="rentalStructure.id" />	
		<fieldset>
			<legend>New Unit</legend>
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
					<th>Related Building</th>
					<td><a href="${uri}structure/${unit.rentalStructure.id}">
						${unit.rentalStructure.id}</a></td>
				</tr>
				<tr>
          <th>Identifier</th>
          <td>
            <form:input path="identifier" /> 
            <form:errors path="identifier" cssClass="error" />
					</td>
				</tr>
				<tr>
          <th>Bedrooms</th>
					<td>
            <form:input path="bedrooms" /> 
            <form:errors path="bedrooms" cssClass="error" />
					</td>					
				</tr>
				<tr>
          <th>Occup Load</th>
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
          <th>Half bath(s)</th>
					<td>
            <form:input path="halfBath" /> 
            <form:errors path="halfBath" cssClass="error" />
					</td>					
				</tr>				
				<tr>
          <th>Sleeping room?</th>
					<td>
            <form:checkbox path="sleepRoom" value="y" /> 
            <form:errors path="sleepRoom" cssClass="error" />
					</td>			
				</tr>
				<tr>
          <th>Efficiency?</th>
					<td>
            <form:checkbox path="efficiency" value="y" /> 
            <form:errors path="efficiency" cssClass="error" />
					</td>			
				</tr>				
				<tr>
          <th>Accessory Dwelling?</th>
					<td>
            <form:checkbox path="accessoryDwelling" value="y" /> 
            <form:errors path="accessoryDwelling" cssClass="error" />
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
            <form:errors path="uninspected" cssClass="error" />
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
		<table class="submitTable">
			<tr>
        <td><button type="submit">Save</button></td>
      </tr>
		</table>
	</fieldset>
  </form:form>

</body>
</html>
