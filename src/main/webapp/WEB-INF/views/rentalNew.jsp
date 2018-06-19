<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}save" method="post" modelAttribute="rental">
  <fieldset>
    <legend>New Rental</legend>
		<b>Note:</b> to add an agent to this rental, start typing in the "Agent name" field to pick from a list <br /><br />
    <table class="vertaTable">
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
				<th>Agent Name</th>
				<td><input type="text" name="agent_name" value="" id="owner_name" />Agent ID:<input type="text" name="agentId" value="" id="owner_id" /></td>
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
    </table>
  </fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
				<td>
					<button type="submit">Save</button>
				</td>
			</tr>
		</table>
	</fieldset>
</form:form>
<br />
<jsp:include page="footer.jsp" />		
