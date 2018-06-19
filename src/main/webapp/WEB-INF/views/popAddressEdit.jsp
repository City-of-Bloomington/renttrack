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
<script type="text/javascript">
function updateOpener(){
	var address_id = document.getElementById("address_id").value;
	var streetAddress = document.getElementById("streetAddress").value;
	window.opener.document.getElementById("address_id").value = address_id;
	window.opener.document.getElementById("address_text").value = streetAddress;
	window.close();
	}
</script>
<body onload="updateOpener()">
	<fieldset>
		<legend>Saved Address</legend>
		<c:if test="${not empty message}">
			<h4>	${mesage} </h4>
		</c:if>
		<form:form action="${uri}addressPopEdit" method="post" modelAttribute="address">
			<form:hidden path="id" id="address_id" />
			<form:hidden path="streetAddress" id="streetAddress"/> 			
			<dl>
				<dt>Address </dt>
				<dd>${address.streetAddress}</dd>
			</dl>
			<input type="button" value="Done" onclick="javascript:window.close();" />
		</form:form>
		<div><a href="javascript:window.close();">Close This Window</a></div>
	</fieldset>
	<jsp:include page="footer.jsp" />		


