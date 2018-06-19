<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />		
<form:form action="${uri}update" method="post" modelAttribute="rental">
	<form:hidden path="id" />
	<c:if test="${rental.hasAgent()}">
		<form:hidden path="agent.id" />
	</c:if>			
	<fieldset>
		<legend>Edit Rental</legend>
		<table class="vertaTable">
			<tr>
				<th>ID</th>
				<td>${rental.id}</td>
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
						<jsp:include page="rentalOwners.jsp" />	
						</td>
				</tr>
			</c:if>
			<tr>
				<th>Status</th>
				<td>
					<form:select path="status_id"> 
						<form:option value="2" label="Registered" />
						<form:options items="${statuses}" itemValue="id" />
					</form:select>
					<form:errors path="rentalStatus" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>Agent</th>
				<td>${rental.agent}</td>
			</tr>
			<tr>
				<th>Registered Date</th>
				<td>
					<form:input path="registeredDateFr" size="10" maxlength="10" cssClass="date" /></td>
			</tr>
			<tr>				
				<th>Last Cycle Date</th>
				<td><form:input path="lastCycleDateFr" size="10" maxlength="10" cssClass="date" /></td>	
			</tr>
			<tr>				
				<th>Permit Issue Date</th>
				<td><form:input path="permitIssuedFr" size="10" maxlength="10" cssClass="date" /></td>
			</tr>
			<tr>				
				<th>Permit Expires</th>
					<td><form:input path="permitExpiresFr" size="10" maxlength="10" cssClass="date" /></td>
			</tr>
			<tr>				
				<th>Permit Length</th>
				<td><form:input path="permitLength" size="2" maxlength="2" /> (yrs)</td>				
			</tr>
			<tr>
				<th>Zoning</th>
				<td><form:select path="zoning_id"> 
					<form:option value="" label="Pick Zoning" />
					<form:options items="${zonings}" itemValue="id" />
				</form:select>				
				</td>
			</tr>
			<tr>	
				<th>N-Hood</th>
				<td>
					<form:select path="NHood"> 
						<form:option value="" label="Pick Neighborhood" />
						<form:options items="${nhoods}" />
					</form:select>									
				</td>
			</tr>
			<tr>
				<th>Grandfathered?</th>
				<td><form:checkbox path="grandfathered" value="y" /></td>
			</tr>
			<tr>
				<th>CDBG Funding?</th>
				<td><form:checkbox path="cdbgFunding" value="y" /></td>
			</tr>
			<tr>
				<th>Affordable?</th>
				<td><form:checkbox path="affordable" value="y" /></td>
			</tr>
			<tr>
				<th>Inactive?</th>
				<td><form:checkbox path="inactive" value="y" /></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<div class="menu">
			<ul class="menu">
				<li class="menu">
					<button type="submit">Save Changes</button>
				</li>
				<li class="menu">
					<input type="button" value="Add Owner" onclick="window.location='${uri}addOwners/${rental.id}'" />
				</li>
			<li class="menu">
				<input type="button" value="Add Address" onclick="window.location='${uri}addressNew/rental/${rental.id}'" />
			</li>							
				<li class="menu">
					<input type="button" value="New Structures" onclick="window.location='${uri}structureNew/${id}'" />
				</li>
				<li class="menu">
					<input type="button" value="New Pull History" onclick="window.location='${uri}pullNew/${rental.id}'" />
				</li>
				<li class="menu">
					<input type="button" value="New Notes" onclick="window.location='${uri}newNote/${id}'" />
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
</form:form>
<c:if test="${rental.hasStructures()}">
	<details>
		<summary>Structures/Buildings</summary>
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
<br />
<br />
</body>
</html>
