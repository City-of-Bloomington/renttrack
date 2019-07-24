<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}reportOwnerWithNoEmail" method="post" modelAttribute="report">
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<fieldset>
			<legend>Rental Active Owners with No Email</legend>
			<p>This report will list all the active owners that do not have emails.</p>
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

