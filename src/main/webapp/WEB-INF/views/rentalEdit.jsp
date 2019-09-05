<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />		
<form:form action="${uri}update" method="post" modelAttribute="rental">
	<form:hidden path="id" />
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>		
	<c:if test="${rental.hasAgent()}">
		<form:hidden path="agent.id" />
	</c:if>			
	<fieldset>
		<legend>Edit Rental</legend>
		<ul>
			<li>ID: ${rental.id}</li>
			<c:if test="${rental.hasAddresses()}">
				<li>
					<jsp:include page="addresses.jsp" />
				</li>
			</c:if>				
			<c:if test="${rental.hasOwners()}">
				<li>
					<jsp:include page="rentalOwnersView.jsp" />	
				</li>
			</c:if>
		</ul>
		<div class="form-group">
			<fieldset>
				<div class="field-group">		
					<label for="status_id">Status:</label>
					<form:select path="status_id" title="Rental status" > 
						<form:option value="2" label="Registered" />
						<form:options items="${statuses}" itemValue="id" />
					</form:select>
					<form:errors path="rentalStatus" cssClass="error" />
				</div>
				<div class="field-group">		
					<label>Agent:</label>
					<c:if test="${rental.hasAgent()}">
						${rental.agent} (${rental.agent.address}) (${rental.agent.phonesInfo}) <button type="button" onclick="window.location='${uri}removeAgent/${rental.id}'" class="button" title="Remove this agent from this rental">Remove Agent </button>
					</c:if>
					<c:if test="${!rental.hasAgent()}">
						Name:
						<form:input path="agent_name" size="30" id="owner_name" title="Type Agent name to pick from the list" />Agent ID:
						<form:input path="agentId" size="10" maxlength="10" id="owner_id" />
					</c:if>
				</div>
				<div>		
					<label for="registeredDateFr">Registered Date: </label>
					<form:input path="registeredDateFr" size="10" maxlength="10" cssClass="date" title="Registered date" />
				</div>
				<div>		
					<label for="lastCycleDateFr">Last Cycle Date: </label>
					<form:input path="lastCycleDateFr" size="10" maxlength="10" cssClass="date" title="Last cycle date"/>	
				</div>
				<div>		
					<label for="permitIssuedFr">Permit Issue Date: </label>
					<form:input path="permitIssuedFr" size="10" maxlength="10" cssClass="date" title="Permit issued date" />
				</div>
				<div>		
					<label for="permitExpiresFr">Permit Expires: </label>
					<form:input path="permitExpiresFr" size="10" maxlength="10" cssClass="date" title="Permit expire date"/>
				</div>
				<div>		
					<label for="permitLength">Permit Length: </label>
					<form:input path="permitLength" size="2" maxlength="2" title="Permit length in years" /> (yrs)
				</div>
				<div class="field-group">		
					<label for="zoning_id">Zoning: </label>
					<form:select path="zoning_id" title="Zoning" > 
						<form:option value="" label="Pick Zoning" />
						<form:options items="${zonings}" itemValue="id" />
					</form:select>				
				</div>
				<div class="field-group">	
					<label for="NHood">N-Hood: </label>
					<form:select path="NHood" title="Neighbordhood" > 
						<form:option value="" label="Pick Neighborhood" />
						<form:options items="${nhoods}" />
					</form:select>									
				</div>
				<div>	
					<form:checkbox path="grandfathered" value="y" title="Is grandfathered"/>
					<label for="grandfathered">Grandfathered</label>					
				</div>
				<div>	
					<form:checkbox path="cdbgFunding" value="y" title="Is CDBG Funding"/>
					<label for="cdbgFunding">CDBG Funding</label>					
				</div>
				<div>	
					<form:checkbox path="affordable" value="y" title="Affordable Designation"/>
					<label for="affordable">Affordable</label>
				</div>
				<div>	
					<form:checkbox path="inactive" value="y" title="Is inactive" />
					<label for="inactive">Inactive</label>
				</div>
			</fieldset>
		</div>
	</fieldset>
	<fieldset>
		<button type="submit" class="button" title="Save Changes">Save Changes</button>
		<input type="button" value="Add Owner" onclick="window.location='${uri}addOwners/${rental.id}'" class="button" title="Add Owner"/>
		<input type="button" value="Add Address" onclick="window.location='${uri}addressNew/rental/${rental.id}'" class="button" title="Add Address"/>
		<input type="button" value="New Structures" onclick="window.location='${uri}structureNew/${id}'" class="button" title="Add New Structure"/>

		<input type="button" value="New Pull History" onclick="window.location='${uri}pullNew/${rental.id}'" class="button" title="Add New Pull History"/>

		<input type="button" value="New Notes" onclick="window.location='${uri}newNote/${id}'" class="button" title="Add New Note"/>

		<input type="button" value="New Bill" onclick="window.location='${uri}billNew/${rental.id}'" class="button" title="Add New Bill"/>

		<input type="button" value="New Attachment" onclick="window.location='${uri}attachmentNew/rental/${rental.id}'" class="button" title="Add Attachment"/>

		<input type="button" value="New Inspection" onclick="window.location='${uri}inspectionNew/${rental.id}'" class="button" title="Add New Inspection"/>
					
		<input type="button" value="New Variance" onclick="window.location='${uri}varianceNew/${rental.id}'" class="button" title="Add New Variance"/>

		<input type="button" value="Start Legal" onclick="window.location='${uri}startLegal/${rental.id}'" class="button" title="Start New Legal Action"/>

		<input type="button" value="Inspection Template" onclick="window.location='${uri}templateNew/${rental.id}'" class="button" title="Inspection Template"/>

		<input type="button" value="Permit" onclick="window.location='${uri}permit/${rental.id}'" class="button" title="Issue Permit"/>
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
<jsp:include page="footer.jsp" />

