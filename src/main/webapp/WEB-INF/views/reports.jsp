<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<c:if test="${not empty message}">
	<h3>${message}</h3>
</c:if>
<fieldset>
  <legend>Reports</legend>
	<p>Pick from the following report options </p>
	<ul>
		<li><a href="${uri}reportInspection">Inspection</a></li>
		<li><a href="${uri}reportPull">Pull History</a></li>
		<li><a href="${uri}reportNoPull">Rental without Pull History</a></li>
		<li><a href="${uri}reportWVariance">Rental with Variance</a></li>
		<li><a href="${uri}reportOverdue">Overdue Bills</a></li>		
		<li><a href="${uri}reportOwnerWithNoEmail">Owners without Email</a></li>
		<li><a href="${uri}reportAgentWithNoEmail">Agents without Email</a></li>		
		<li><a href="${uri}reportRental">Rentals with address, Owner, Agent, buildings, etc </a></li>
		
	</ul>
</fieldset>
</body>
</html>
