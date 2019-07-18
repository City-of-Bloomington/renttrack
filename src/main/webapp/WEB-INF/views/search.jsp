<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}rentalSearch" method="post" modelAttribute="search">
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<fieldset>
			<legend>Search Rentals</legend>
			<table>
				<tr>
					<th>Rental ID</th>
					<td>
						<form:input path="id" size="10" /> 
					</td>
				</tr>
				<tr>
					<th>Status</th>
					<td>
						<form:select path="statusId"> 
							<form:option value="" label="All" />
							<form:options items="${statuses}" itemValue="id" />
						</form:select>
					</td>
				</tr>				
				<tr>
					<th>Rental Agent</th>
					<td>
						<form:input path="agentName" id="agent_name" size="30" /> Agent ID:
						<form:input path="agentId" id="agent_id" value="" size="10" /> 
					</td>
				</tr>
				<tr>
					<th>Rental Owner</th>
					<td>
						<form:input path="ownerName" id="owner_name" size="30" /> Owner ID:
						<form:input path="ownerId" id="owner_id" size="10" /> 
					</td>
				</tr>
				<tr>
					<th>Zoning</th>
					<td><form:select path="zoningId"> 
						<form:option value="0" label="All" />
						<form:options items="${zonings}" itemValue="id" />
					</form:select>				
					</td>
				</tr>
				<tr>	
					<th>N-Hood</th>
					<td>
						<form:select path="NHood"> 
						<form:option value="0" label="All" />
						<form:options items="${nhoods}" />
					</form:select>									
					</td>
				</tr>				
				<tr>
					<th>Address</th>
					<td>
						<form:input path="addressText" id="address_text" /> ID
						<form:input path="addressId" id="address_id" />
					</td>
				</tr>
				<tr>
					<th>Date Option:</th>
					<td> 
						<form:radiobutton path="dateType" value="registeredDate" />Registered,
						<form:radiobutton path="dateType" value="permitIssued" />Permit Issued,
						<form:radiobutton path="dateType" value="permitExpires" />Permit Expires,
						<form:radiobutton path="dateType" value="lastCycleDate" />Last Cycle
					</td>
				</tr>
				<tr>
					<th>Date Range:</th>
					<td>
						from:<form:input path="dateFrom" cssClass="date" size="10" /> -
						to:<form:input path="dateTo" cssClass="date" size="10" />
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<table class="submitTable">
				<tr>
					<td><button type="submit">Submit</button></td>
				</tr>
			</table>
		</fieldset>		
  </form:form>
	<jsp:include page="footer.jsp" />

