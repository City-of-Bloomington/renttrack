<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
	<p>To delete a component, modify a component title or add new ones click on 'Edit' link below</p>
	<fieldset>
    <legend>Inspection Template ${template.id}</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th><td><a href="${uri}templateEdit/${template.id}">Edit ${template.id}</a></td>
			</tr>			
			<tr>
				<th>Related Rental:</th>
				<td><a href="${uri}view/${template.rentalId}"> ${template.rentalId}</a></td>		
			</tr>
			<tr><th>Date</th><td>${template.dateFr}</td></tr>
			<tr><th>User</th><td>${template.user}</td></tr>
		</table>
	</fieldset>		
	<c:if test="${template.hasTemplateComponents()}">
		<jsp:include page="templateComponents.jsp" />
	</c:if>
	<br />
	<br />
<jsp:include page="footer.jsp" />

