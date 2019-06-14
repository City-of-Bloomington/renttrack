<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />
<style>

table {
	border-collapse:collapse;
	border:0px;
	margin:1em 0 1em 0;
} 
tr { }
td {
	padding:2px;
	background-color:inherit;
	border:0px;
	vertical-align:top;
	test-align:left;
} 
th { text-align:center;
	border:1px solid #999999;
	background-color:#e0e0e0;
	padding:2px;
	vertical-align: top;
	font-weight:bold;
}
.topTable{
	width:100%;
}
.topTable th {
	text-align:left;
	border:0px;
	padding:2px;
	vertical-align: top;
	font-size:small;
}
.topTable td {
	text-align:right;
	border:0px;
	vertical-align: top;
	padding:2px;
	font-size:small;
}
.wideTable {
	width:100%;
}
.wideNoBorder {
	width:100%;
}
.wideNoBorder th {
	text-align:center;
	border:0px;
	padding:2px;
	vertical-align: top;
	font-weight:bold;
	font-size:small;
}
.wideNoBorder td {
	padding:2px;
	background-color:inherit;
	border:0px;
	vertical-align:top;
	text-align:left;
	font-size:small;
}

.semiTable{
	width:60%;
}
.wideTable th {
	text-align:center;
	border:1px solid;
	padding:2px;
	vertical-align: top;
	font-weight:bold;
	font-size:small;
}
.wideTable td {
	padding:2px;
	background-color:inherit;
	border:1px solid;
	vertical-align:top;
	text-align:left;
	font-size:small;
}

.semiTable th {
	text-align:center;
	border:1px solid;
	padding:2px;
	vertical-align: top;
	font-weight:bold;
	font-size:small;
}
.semiTable td {
	padding:2px;
	background-color:inherit;
	border:1px solid;
	vertical-align:top;
	text-align:left;
	font-size:small;
}
.permit{
	font-size:small;
}
.pcenter{
	font-size:small;
	text-align:center;
	align-items:center; 
}
.centerMed{
	font-size:medium;
	text-align:center;
	align-items:center;
}

.main{
	border-collapse:collapse;
	border:1px solid; margin:1em 0 1em 0;
}
.main td {
	padding:2px;
	background-color:inherit;
	border:1px solid;
	vertical-align:top;
	text-align:center;
} 
.main th {
	text-align:center;
	border:1px solid;
	padding:2px;
	vertical-align: top;
	font-weight:bold;
} 
.lefted {
	text-align:left;
} 
</style>
<body>
	<br /><br />
	<br /><br />
	<br /><br />
	<br />
	<center>
	<p class="centerMed">
		A COPY OF THIS PERMIT AND THE RENTAL FILE ARE AVAILABLE FOR THE PUBLIC TO VIEW DURING <br />
		REGULAR BUSINESS HOURS AT THE HOUSING AND NEIGHBORHOOD DEVELOPMENT OFFICE<br />

		<hr width="100%" size="1" noshade="noshade" />
		<b>RESIDENTIAL RENTAL OCCUPANCY PERMIT</b><br />
		HOUSING AND NEIGHBORHOOD DEVELOPMENT DEPARTMENT
		<br />
		<i>City of Bloomington, Indiana</i>
		<br />
	</p>
	</center>
	<table class="topTable">
		<tr><th>${today}</th>
		</tr>
		<tr>
			<th><b>Location:</b> ${rental.rentalAddresses}</th>
			<td><b>Zone:</b> ${rental.zoning}</td>
		</tr>
		<tr>
			<th><b>Owner(s):</b> 
				<c:if test="${rental.hasOwners()}">
					<c:forEach items="${rental.rentalOwners}" var="one">
						${one.owner.name}&nbsp;&nbsp;${one.owner.address}&nbsp;&nbsp;${one.owner.cityStateZip} <br />
					</c:forEach>
				</c:if>
			</th>
			<td><b>Structures/Units:</b> ${rental.structureCount}/${rental.unitCount} </td>			
		</tr>
		<c:if test="${rental.hasAgent()}">
			<tr>
				<th>
					<b>Agent: </b>  
				${rental.agent.name}&nbsp;&nbsp;${rental.agent.address}&nbsp;&nbsp;${rental.agent.cityStateZip}</th>
			</tr>
		</c:if>
		<tr>
			<th><b> Inspector:</b> ${inspection.inspector}</th>
		</tr>
	</table>
	<c:if test="${rental.hasStructures()}">
		<c:set var="uninspected" value="${rental.hasUnispected()}" scope="request" />		
		<c:if test="${rental.unitCount < 9}">
			<table class="semiTable">
				<tr>
					<th>Structure</th>
					<th>Units</th>
					<th>Bedrooms per Unit</th>
					<th>Max Occupant Load per Unit</th>
					<c:if test="${uninspected}"><th>Inspection</th></c:if>
				</tr>
				<c:forEach items="${rental.rentalStructures}" var="structure">
					<c:if test="${structure.hasUnits()}">						
						<c:forEach items="${structure.rentalUnits}" var="unit">
							<tr>
								<td>${structure.identifier}</td>
								<td>${unit.identifier}
									<c:if test="${unit.isSleepingRoom()}"> RH </c:if>
								</td>
								<td>
									<c:if test="${unit.isEfficienyType()}">EFF</c:if>
									<c:if test="${!unit.isEfficienyType()}">
										<c:if test="${unit.bedroomCount>0}"> ${unit.bedroomCount}
											<c:if test="${unit.isSleepingRoom()}">SR</c:if>
										</c:if>
  								</c:if>
								</td>
								<td>${unit.occupLoad}</td>
								<c:if test="${rental.hasUnispected()}">
									<td>
										<c:if test="${unit.isNotInspected()}">(Uninspected)</c:if>
										<c:if test="${!unit.isNotInspected()}">Inspected</c:if>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</c:forEach>						
			</table>								
		</c:if>
		<c:if test="${rental.unitCount > 8}">
			<table class="wideTable">
				<tr>
					<th>Structure</th>
					<th>Units</th>
					<th>Bedrooms per Unit</th>
					<th>Max Occupant Load per Unit</th>
					<c:if test="${uninspected}"><th>Inspection</th></c:if>
					<th>Structure</th>
					<th>Units</th>
					<th>Bedrooms per Unit</th>
					<th>Max Occupant Load per Unit</th>
					<c:if test="${uninspected}"><th>Inspection</th></c:if>
				</tr>
				<c:set var="row" value="${0}" />
				<c:forEach items="${rental.rentalStructures}" var="structure">
					<c:if test="${structure.hasUnits()}">						
						<c:forEach items="${structure.rentalUnits}" var="unit">
							<c:if test="${row % 2 == 0}">
								<tr>
							</c:if>
							<td>${structure.identifier}</td>
							<td>${unit.identifier}
								<c:if test="${unit.isSleepingRoom()}"> RH </c:if>
							</td>
							<td>
								<c:if test="${unit.isEfficienyType()}">EFF</c:if>
								<c:if test="${!unit.isEfficienyType()}">
									<c:if test="${unit.bedroomCount > 0}"> ${unit.bedroomCount}
										<c:if test="${unit.isSleepingRoom()}">SR</c:if>
									</c:if>
								</c:if>
							</td>
							<td>${unit.occupLoad}</td>
							<c:if test="${uninspected}">
								<td>
									<c:if test="${unit.isNotInspected()}">(Uninspected)</c:if>
									<c:if test="${!unit.isNotInspected()}">Inspected</c:if>
								</td>
							</c:if>
							<c:if test="${row%2 == 1}"></tr></c:if>
							<c:set var="row" value="${row+1}" />
						</c:forEach>
					</c:if>
				</c:forEach>						
			</table>				
		</c:if>
	</c:if>
	<p class="permit">
		The permit certifies compliance with the provision of Title 16 of the Bloomington Municipal Code, \"Bloomington Residential Rental Unit and Lodging Establishment Inspection Program\", and does not represent compliance with any other Title of the Bloomington Municipal Code or other relevant statutes or ordinances, particularly in regards to laws which regulate the zoning of this property. No change of use shall be made in this location without the prior approval of the applicable departments.
		
	</p>
	<table class="wideNoBorder">
		<tr>
			<td>Date Inspected: ${inspection.inspectionDateFr}</td>
			<td>Date Complied: ${inspection.complianceDateFr}</td>
			<td>PERMIT EXPIRES: ${rental.permitExpiresFr}</td>
		</tr>
	</table>
	<table class="wideNoBorder">
		<tr>
			<td style="text-align:right">
				<br />
					__________________________________<br />
				Housing Official							
			</td>
		</tr>
	</table>
	<hr width="100%" size="1" noshade="noshade" />
	<br />
	<center>
		<p class="permit"><b>A copy of the permit must be displayed on the inside of the main entrance of the rental units</b><br />
			
		Reminder: Each residential rental unit shall be scheduled to receive a cycle inspection at least sixty days prior to the expiration of its permit. Don't forget to call HAND before this time. (16.03.040)

	</p>
	</center>
</body>
</html>
