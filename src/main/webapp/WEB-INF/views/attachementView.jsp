<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Attachement</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th>
				<td>${attachement.id}</td>
			</tr>
			<c:if test="${not empty attachement.inspectionId}">
				<tr>
					<th>Related Inspection</th>
					<td>${attachement.inspectionId}</td>
				</tr>
			</c:if>
			<c:if test="${not empty attachement.rental.id}">
				<tr>
					<th>Related Rental</th>
					<td>${attachement.rental.id}</td>
				</tr>
			</c:if>			
			<tr>
				<th>Old File Name</th>
				<td>${attachement.oldFileName}</td>
			</tr>
			<tr>
				<th>File Name</th>
        <td>${attachement.fileName}</td>
			</tr>
			<tr>
				<th>Date</th>
				<td>${attachement.dateFr}</td>
			</tr>
			<c:if test="${not empty attachement.notes}">
				<tr>
					<th>Notes</th>
					<td>${attachement.notes}</td>
				</tr>
			</c:if>
    </table>
  </fieldset>
</body>
</html>
