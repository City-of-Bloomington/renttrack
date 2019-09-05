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
    <legend>Rental Details</legend>
      <ul>
				<li>ID: <a href="${uri}edit/${rental.id}">Edit ${rental.id}</a></li>
				<c:if test="${rental.hasAddresses()}">
					<li>Address(es) 
						<jsp:include page="addresses.jsp" />
					</li>
				</c:if>			
				<c:if test="${rental.hasOwners()}">
					<li>
						<jsp:include page="rentalOwnersView.jsp" />	
					</li>
				</c:if>
				<li>Status: ${rental.rentalStatus}</li>
				<li>Agent:
					<c:if test="${rental.hasAgent()}">
						${rental.agent} (${rental.agent.address}) (${rental.agent.phonesInfo})
					</c:if>
					<c:if test="${!rental.hasAgent()}">
						No Agent
					</c:if>
				</li>
				<li>Registered Date: ${rental.registeredDateFr}</li>
				<li>Last Cycle Date: ${rental.lastCycleDateFr}</li>
				<li>Permit Issue Date: ${rental.permitIssuedFr}</li>

				<li>Permit Expires: ${rental.permitExpiresFr}</li>

				<li>Permit Length: ${rental.permitLength} (yrs)</li>
				<li>Zoning: ${rental.zoning}</li>				
				<li>N-Hood: ${rental.NHood}</li>								
				<c:if test="${not empty rental.features}">
					<li>Special Features: ${rental.features}</li>
				</c:if>
				<c:if test="${not empty rental.inactive}">			
					<li>Status: Inactive Rental</li>
				</c:if>
			</ul>
	</fieldset>
	<div class="form-group">
		<fieldset>
			<input type="button" value="Add Owners" onclick="window.location='${uri}addOwners/${rental.id}'" class="button"/>
			
			<input type="button" value="Add Address" onclick="window.location='${uri}addressNew/rental/${rental.id}'" class="button"/>
			
			<input type="button" value="New Structures" onclick="window.location='${uri}structureNew/${rental.id}'" class="button"/>

			<input type="button" value="New Notes" onclick="window.location='${uri}noteNew/${rental.id}'" class="button"/>
			
			<input type="button" value="New Pull History" onclick="window.location='${uri}pullNew/${rental.id}'" class="button"/>

			<input type="button" value="New Bill" onclick="window.location='${uri}billNew/${rental.id}'" class="button"/>
			
			<input type="button" value="New Attachment" onclick="window.location='${uri}attachmentNew/rental/${rental.id}'" class="button"/>

			<input type="button" value="New Inspection" onclick="window.location='${uri}inspectionNew/${rental.id}'" class="button"/>

			<input type="button" value="New Variance" onclick="window.location='${uri}varianceNew/${rental.id}'" class="button"/>

			<input type="button" value="Start Legal" onclick="window.location='${uri}startLegal/${rental.id}'" class="button"/>

			<input type="button" value="Inspection Template" onclick="window.location='${uri}templateNew/${rental.id}'" class="button"/>

			<input type="button" value="Permit" onclick="window.location='${uri}permit/${rental.id}'" class="button"/>
		</fieldset>
	</div>
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
	<c:if test="${rental.hasAttachments()}">
		<details>
			<summary>Attachments</summary>
			<c:set var="attachments" value="${rental.attachments}" scope="request" />
			<jsp:include page="attachments.jsp" />
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
