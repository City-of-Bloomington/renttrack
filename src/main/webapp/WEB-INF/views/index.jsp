<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>RentTrack</legend>
		<h3>Welcome to RentTrack </h3>
		<h3>Introduction</h3>
		This application tracks rental properties for the city of Bloomington, IN for HAND department. Pick from the menu above to do the following: <br />
		
		<ul>
			<li>Owners: to add/edit an owner/agent</li>
			<li>New Rental: to add a new rental record</li>
			<li>Search: to search for a rental record</li>
			<li>Report: to run reports</li>
			<li>Settings: to add/edit users and add lookup features for the application (limited to admin users only)</li>
		</ul>
  </fieldset>
</body>
</html>
