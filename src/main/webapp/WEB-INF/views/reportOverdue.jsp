<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}reportOverdue" method="post" modelAttribute="report">
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<fieldset>
			<legend>Overdue Bill Report</legend>
			<table>
				<tr>
					<th>Due Date From:</th>
					<td>
						<form:input path="dateFrom" cssClass="date" />(mm/dd/yyyy)
					</td>
				</tr>
				<tr>
					<th>Due Date To:</th>
					<td>
						<form:input path="dateTo" cssClass="date" />(mm/dd/yyyy)
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

