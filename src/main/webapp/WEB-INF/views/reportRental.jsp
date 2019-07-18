<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}reportRental" method="post" modelAttribute="report">
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<fieldset>
			<legend>Rental Report</legend>
			<p>You may choose to set some of the following variables. <br />
				If no date range is set, the report will lookup all the
				active rentals that have expire date in the future or less than 3 years.
			</p>
			<table>
				<tr>
					<th>Building Type</th>
					<td><form:select path="buildingTypeId"> 
						<form:option value="0" label="All" />
						<form:options items="${buildingTypes}" itemValue="id" itemName="name" />
					</form:select>				
					</td>
				</tr>
				<tr>	
					<th>Pull Reason</th>
					<td>
						<form:select path="pullReasonId"> 
						<form:option value="0" label="All" />
						<form:options items="${pullReasons}" itemValue="id" itemName="name" />
					</form:select>									
					</td>
				</tr>
				<tr>	
					<th>Property Type</th>
					<td>
						<form:select path="propertyTypeId"> 
						<form:option value="0" label="All" />
						<form:options items="${propertyTypes}" itemValue="id" itemName="name" />
					</form:select>									
					</td>
				</tr>				
				<tr>
					<th>(Units) Has </th>
					<td>
						<form:input path="unitsFrom" size="2" /> or more units 
					</td>
				</tr>
				<tr>
					<th>(Bedrooms) Has </th>
					<td>
						<form:input path="bedroomsFrom" size="2" /> or more bedrooms 
					</td>
				</tr>
				<tr>
					<th>Date Option:</th>
					<td> 
						<form:radiobutton path="dateType" value="registered" />Registered,
						<form:radiobutton path="dateType" value="expires" />Expires,
						<form:radiobutton path="dateType" value="cycle" />Last Cycle
					</td>
				</tr>				
				<tr>
					<th>Date Range:</th>
					<td>
						from:<form:input path="dateFrom" cssClass="date" size="10" /> -
						to:<form:input path="dateTo" cssClass="date" size="10" />
					</td>
				</tr>
				
				<tr>
					<th>Output Type:</th>
					<td><form:checkbox path="outputType" value="csv" /> CSV (comma separated fields to use with spread sheets apps such as Excel)
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

