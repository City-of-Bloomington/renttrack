<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Rental Details</legend>
    <table class="vertaTable">
      <tr>
				<th>ID</th>
				<td><a href="${uri}edit/${rental.id}">Edit ${rental.id}</a></td>
			</tr>
			<c:if test="${rental.hasAddresses()}">
				<tr>
					<td colspan="2">
						<jsp:include page="addresses.jsp" />	
					</td>
				</tr>
			</c:if>			
			<c:if test="${rental.hasOwners()}">
				<tr>
					<td colspan="2">
						<jsp:include page="rentalOwnersView.jsp" />	
					</td>
				</tr>
			</c:if>
			<tr>
				<th>Status</th>
				<td>${rental.rentalStatus}</td>
			</tr>
			<c:if test="${rental.hasAgent()}">
				<tr>
					<th>Agent</th>
					<td>${rental.agent} (${rental.agent.address}) (${rental.agent.phonesInfo})</td>
				</tr>
			</c:if>
			<tr>
				<th>Registered Date</th>
				<td>${rental.registeredDateFr}</td>
			</tr>
			<tr>				
				<th>Last Cycle Date</th>
				<td>${rental.lastCycleDateFr}</td>
			</tr>
			<tr>				
				<th>Permit Issue Date</th>
				<td>${rental.permitIssuedFr}</td>
			</tr>
			<tr>				
				<th>Permit Expires</th>
					<td>${rental.permitExpiresFr}</td>
			</tr>
			<tr>				
				<th>Permit Length</th>
					<td>${rental.permitLength} (yrs)</td>
			</tr>
			<tr>
				<th>Zoning</th>
					<td>${rental.zoning}</td>				
			</tr>
			<tr>	
				<th>N-Hood</th>
				<td>${rental.NHood}</td>								
			</tr>
			<c:if test="${not empty rental.features}">
				<tr>			
					<th>Special Features</th>
					<td>${rental.features}</td>			
				</tr>
			</c:if>
			<c:if test="${not empty rental.inactive}">			
				<tr>
					<th>Active Status</th>
					<td>Inactive Rental</td>
				</tr>
			</c:if>
		</table>
	</fieldset>
	<fieldset>
		<div class="menu">
		<ul class="menu">
			<li class="menu">
				<input type="button" value="Add Owners" onclick="window.location='${uri}addOwners/${rental.id}'" />
			</li>
			<li class="menu">
				<input type="button" value="Add Address" onclick="window.location='${uri}addressNew/rental/${rental.id}'" />
			</li>			
			<li class="menu">
				<input type="button" value="New Structures" onclick="window.location='${uri}structureNew/${rental.id}'" />
			</li>
			<li class="menu">
				<input type="button" value="New Notes" onclick="window.location='${uri}noteNew/${rental.id}'" />
			</li>
			<li class="menu">
				<input type="button" value="New Pull History" onclick="window.location='${uri}pullNew/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="New Bill" onclick="window.location='${uri}billNew/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="New Attachement" onclick="window.location='${uri}attachementNew/rental/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="New Inspection" onclick="window.location='${uri}inspectionNew/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="New Variance" onclick="window.location='${uri}varianceNew/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="Start Legal" onclick="window.location='${uri}startLegal/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="Inspection Template" onclick="window.location='${uri}templateNew/${rental.id}'" />
			</li>
			<li class="menu">
					<input type="button" value="Permit" onclick="window.location='${uri}permit/${rental.id}'" />
			</li>			
		</ul>
		</div>
	</fieldset>
	<c:if test="${rental.hasStructures()}">
		<details open="true">
			<summary>Structures</summary>
			<jsp:include page="structures.jsp" />
		</details>
	</c:if>
	<c:if test="${rental.hasNotes()}">
		<details>
			<summary>Notes</summary>
			<jsp:include page="notes.jsp" />
		</details>
	</c:if>
	<c:if test="${rental.hasPullHistory()}">
		<details>
			<summary>Pull History</summary>		
			<jsp:include page="pulls.jsp" />
		</details>
	</c:if>
	<c:if test="${rental.hasBills()}">
		<details>
			<summary>Bills</summary>		
			<jsp:include page="bills.jsp" />
		</details>
	</c:if>		
	<c:if test="${rental.hasVariances()}">
		<details>
			<summary>Variances</summary>		
			<jsp:include page="variances.jsp" />
		</details>
	</c:if>
	<c:if test="${rental.hasInspections()}">
		<details>
			<summary>Inspections</summary>
			<c:set var="inspections" value="${rental.inspections}" scope="request" />
			<jsp:include page="inspections.jsp" />
		</details>
	</c:if>		
	<c:if test="${rental.hasAttachements()}">
		<details>
			<summary>Attachments</summary>
			<c:set var="attachements" value="${rental.attachements}" scope="request" />
			<jsp:include page="attachements.jsp" />
		</details>
	</c:if>
	<c:if test="${rental.hasLegals()}">
		<details>
			<summary>Rental Legals</summary>
			<jsp:include page="legals.jsp" />
		</details>
	</c:if>				
	<c:if test="${rental.hasLogs()}">
		<details>
			<summary>Logs</summary>
			<c:set var="rentalLogs" value="${rental.rentalLogs}" scope="request" />
			<jsp:include page="logs.jsp" />
		</details>
	</c:if>		
	<c:if test="${rental.hasLegalEmailLogs()}">
		<details>
			<summary>LegalIt Email Logs</summary>
			<c:set var="legalItEmailLogs" value="${rental.legalItEmailLogs}" scope="request" />
			<jsp:include page="legalItEmailLogs.jsp" />
		</details>
	</c:if>
	<br />
	<br />
</body>
</html>
