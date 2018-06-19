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
  <legend>RentTrack</legend>
	Please pick one of the options below from top menu:
	<ul>
		<li>New Rental: to enter a new rental record </li>
		<li>Owners: list of recent owners</li>
		<li>Rentals: list of recent rentals</li>
		<li>Search: search for rental records</li>
		<li>Reports: to run reports</li>
		<li>Settings: for important application configuration and user setting</li>
		<li>Logout: to logout when you are done</li>
	</ul>
	
</fieldset>
</body>
</html>
