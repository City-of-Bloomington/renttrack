<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}structureUpdate" method="post" modelAttribute="structure">
	<form:hidden path="id" />
	<form:hidden path="rentalId" />
  <fieldset>
    <legend>Structure (building)</legend>
		<details>
			<summary>Instructions</summary>
			<ul>
				<li>Year built and egress info are needed for inspection reports</li>
				<li>Egress Requirment Year Range, such as for certain year egress enter the year such as 1990  and for year range such as 1994-1998 enter the range</li>
				<li>Egress info will be populated automatically when start entering the 'Year Built type' field and pick from the list</li>
			</ul>
		</details>		
    <table class="vertaTable">
			<tr>
				<th>ID</th>
				<td>${structure.id}</td>
			</tr>
			<tr>
				<th>Related Rental</th>
				<td><a href="${uri}view/${structure.rentalId}">${structure.rentalId}</a></td>
			</tr>
			<tr>
        <th>Identifier</th>
        <td>				
					<form:input path="identifier" size="30" /> 
          <form:errors path="identifier" cssClass="error" />
        </td>
			</tr>
			<tr>
        <th>Building Type</th>
				<td>
          <form:select path="buildingTypeId">
						<form:option value="0" label="Pick Building Type" />
						<form:options items="${buildingTypes}" itemValue="id" itemLabel="name" />
					</form:select>
				</td>					
			</tr>
			<tr>
				<th>Property Type</th>
				<td>
          <form:select path="propertyTypeId">
						<form:option value="0" label="Pick Property Type" />
						<form:options items="${propertyTypes}" itemValue="id" itemLabel="name" />
					</form:select>
				</td>
			</tr>
			<tr>
				<th>Heat Source</th>
        <td>
            <form:radiobuttons path="heatSource" items="${heatSources}" />
        </td>
			</tr>				
			<tr>
				<th>Foundation</th>
        <td>
          <form:radiobuttons path="foundation" items="${foundationTypes}" />
        </td>
			</tr>
			<tr>
				<th>Story Count</th>
        <td>
          <form:input path="storyCnt" size="2" />
        </td>
			</tr>
			<tr>
				<th>Year built - type</th>
        <td>
          <form:input path="yearBuiltType" size="10" id="built_year_type" />
					(start typing the year and then pick from list to populate the egress info below)
          </td>
			</tr>				
			<tr>
				<th>Year Built</th>
        <td>
          <form:input path="yearBuilt" size="4" id="built_year" />
					(start typing the year and then pick from list to populate the egress info below)
        </td>
			</tr>
			<tr>
				<th>Egress Requirment Year Range</th>
        <td>
          <form:input path="egressDecreeYears" size="10" id="start_end_year" /> (BMC egress rule such as 1994-1998)
        </td>
			</tr>						
			<tr>
				<th>Egress Height</th>
        <td>
          <form:input path="egressHeight" size="3" id="height_id"/> (inches)
        </td>
			</tr>
			<tr>
				<th>Egress Width</th>
        <td>
          <form:input path="egressWidth" size="3" id="width_id" /> (inches)
        </td>
			</tr>
			<tr>
				<th>Egress Sill Height</th>
        <td>
          <form:input path="egressSillHeight" size="3" id="sill_height_id" /> (inches) max above finished floor.
        </td>
			</tr>
			<tr>
				<th>Egress Open. Area </th>
        <td>
          <form:input path="egressArea" size="6" id="area_id" />(sq. ft) fist floor
        </td>
			</tr>
			<tr>
				<th>Egress Open. Area</th>
        <td>
          <form:input path="egressArea2" size="6" id="area2_id" />(sq. ft) other floors (if any)
        </td>
			</tr>			
		</table>
	</fieldset>
	<c:if test="${structure.hasUnits()}">
		<jsp:include page="units.jsp" />	
	</c:if>				
  <fieldset>
		<div class="menu">
			<ul class="menu">
				<li class="menu">
					<button type="submit">Save Changes</button>
				</li>
				<li class="menu">				
					<input type="button" value="New Unit" onclick="window.location='${uri}unitNew/${structure.id}'" />
				</li>
				<li class="menu">			
					<input type="button" value="New Structure" onclick="window.location='${uri}structureNew/${structure.rentalId}'" />
				</li>
				<li class="menu">			
					<input type="button" value="Delete" onclick="window.location='${uri}structureDelete/${structure.id}'" />		
				</li>
			</ul>
		</div>
  </fieldset>		
</form:form>
<br />
<jsp:include page="footer.jsp" />
