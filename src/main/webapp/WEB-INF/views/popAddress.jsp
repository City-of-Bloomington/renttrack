<!--
 * @copyright Copyright (C) 2014-2015 City of Bloomington, Indiana. All rights reserved.
 * @license http://www.gnu.org/copyleft/gpl.html GNU/GPL, see LICENSE.txt
 * @author W. Sibo <sibow@bloomington.in.gov>
 *
	-->
<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />		
<body>
	<fieldset>
		<legend>New Address</legend>
		<p>Enter the full address in the address field below (example: 401 N Morton St Ste 150)</p>
		<c:if test="${not empty message}">
			<h4>	${mesage} </h4>
		</c:if>
		<form:form action="${uri}addressPopFind" method="post" modelAttribute="address">
			<dl>
				<dt>Address </dt>
				<dd><form:input path="streetAddress" id="streetAddress" size="30" maxlength="70" /> </dd>
			</dl>
			<button type="submit">Next</button>
		</form:form>
		<div><a href="javascript:window.close();">Close This Window</a></div>
	</fieldset>
<jsp:include page="footer.jsp" />		


