<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}save" method="post" modelAttribute="rental">
	<div class="form-group">
		<fieldset>
			<legend>New Rental</legend>
			<ul>
				<li>To add an agent to this rental, start typing in the "Agent name" field to pick from a list </li>
				<li>If the agent is not in the list, you can add the agency to current agents by clicking on 'New Owner' menu option.</li>
				<li>You can add the agent later if not in the current list of agents.</li>
				<li>The owner(s) and address(es) can be added after the rental record is created </li>
			</ul>
			<div class="field-group">		
				<label for"status_id">Status:</label>
				<form:select path="status_id" title="Rental status" > 
					<form:option value="2" label="Registered" />
					<form:options items="${statuses}" itemValue="id" />
				</form:select>
				<form:errors path="rentalStatus" cssClass="error" />
			</div>
			<div>						
				<label for="agent_name">Agent Name: </label>
				<input type="text" name="agent_name" value="" id="owner_name" title="Start typing agent name to pick from the list"/>Agent ID:<input type="text" name="agentId" value="" id="owner_id" />
			</div>
			<div>
				<label for="registeredDateFr"> Registered Date: </label>
				<form:input path="registeredDateFr" size="10" maxlength="10" cssClass="date" title="Registered Date"/>
			</div>
			<div>
				<label for="lastCycleDateFr">Last Cycle Date: </label>
				<form:input path="lastCycleDateFr" size="10" maxlength="10" cssClass="date" title="Last Cycle Date" />	
			</div>
			<div>
				<label for="permitIssuedFr">Permit Issue Date: </label>
				<form:input path="permitIssuedFr" size="10" maxlength="10" cssClass="date" title="Permit Issued Date" />
			</div>
			<div>
				<label for="permitExpiresFr">Permit Expires: </label>
				<form:input path="permitExpiresFr" size="10" maxlength="10" cssClass="date" title="Permit Expire Date" />
			</div>
			<div>
				<label for="permitLength">Permit Length: </label>
				<form:input path="permitLength" size="2" maxlength="2" title="Permit length in years" /> (yrs)
			</div>
			<div>
				<label for="zoning_id">Zoning: </label>
				<form:select path="zoning_id" title="Zoning" > 
					<form:option value="" label="Pick Zoning" />
					<form:options items="${zonings}" itemValue="id" />
				</form:select>				
			</div>
			<div>
				<label for="NHood">N-Hood: </label>
					<form:select path="NHood" title="Neighborhood" > 
						<form:option value="" label="Pick Neighborhood" />
						<form:options items="${nhoods}" />
					</form:select>									
			</div>
			<div>
				<form:checkbox path="grandfathered" value="y" title="Is Grandfathered" />				
				<label for="grandfathered"> Grandfathered</label>
			</div>
			<div>
				<form:checkbox path="cdbgFunding" value="y" title="Has CDBG Funding" />				
				<label for="cdbgFunding"> CDBG Funding</label>
			</div>
			<div>
				<form:checkbox path="affordable" value="y" title="Affordable Designation" />				
				<label for="affordable"> Affordable</label>
			</div>
		</fieldset>
	</div>
	<fieldset>
		<button type="submit" class="button" title="Save">Save</button>
	</fieldset>
</form:form>
<br />
<jsp:include page="footer.jsp" />		
