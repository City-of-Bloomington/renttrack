<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
    <legend>Building</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th>
				<td><a href="${uri}structureEdit/${structure.id}">Edit ${structure.id}</a></td>
			</tr>
			<tr>
				<th>Related Rental</th>
				<td><a href="${uri}view/${structure.rentalId}">${structure.rentalId}</a></td>
			</tr>
			<tr>
				<th>Identifier</th>
        <td>${structure.identifier}</td>
			</tr>
			<tr>
				<th>Building Type</th>
        <td>${structure.buildingType}</td>
			</tr>
			<tr>
				<th>Property Type</th>
        <td>${structure.propertyType}</td>
			</tr>
				<tr>
					<th>Heat Source</th>
          <td>
            ${structure.heatSource}
          </td>
				</tr>				
				<tr>
					<th>Foundation</th>
          <td>
           ${structure.foundation}
          </td>
				</tr>
				<tr>
					<th>Story Count</th>
          <td>
            ${structure.storyCnt}
          </td>
				</tr>
				<tr>
					<th>Year Built</th>
          <td>
            ${structure.yearBuilt}
          </td>
				</tr>				
				<c:if test="${structure.hasEgressInfo()}">
					<tr>
						<th>Egress Info</th>
						<td>${structure.egressInfo}</td>
					</tr>
				</c:if>
		</table>
	</fieldset>
	<c:if test="${not empty structure.rentalUnits}">
		<jsp:include page="units.jsp" />	
	</c:if>
	<fieldset>
		<div class="menu">
			<ul class="menu">
				<li class="menu">
					<input type="button" value="Edit" onclick="window.location='${uri}structureEdit/${structure.id}'" />
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
</body>
</html>
