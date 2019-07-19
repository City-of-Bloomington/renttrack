<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}reportPull" method="post" modelAttribute="report">
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<fieldset>
			<legend>Pull Report</legend>
			<table>
				<tr>	
					<th>Pull Reason</th>
					<td>
						<form:select path="pullReasonId"> 
						<form:option value="0" label="All" />
						<form:options items="${pullReasons}" itemValue="id" itemName="reason" />
					</form:select>									
					</td>
				</tr>				
				<tr>
					<th>Pull Date Range:</th>
					<td>
						from:<form:input path="dateFrom" cssClass="date" /> -
						to:<form:input path="dateTo" cssClass="date" />
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

