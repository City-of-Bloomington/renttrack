<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Attachment</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th>
				<td>${attachment.id}</td>
			</tr>
			<c:if test="${not empty attachment.inspectionId}">
				<tr>
					<th>Related Inspection</th>
					<td>${attachement.inspectionId}</td>
				</tr>
			</c:if>
			<c:if test="${not empty attachment.rental.id}">
				<tr>
					<th>Related Rental</th>
					<td>${attachment.rental.id}</td>
				</tr>
			</c:if>			
			<tr>
				<th>Old File Name</th>
				<td>${attachment.oldFileName}</td>
			</tr>
			<tr>
				<th>File Name</th>
        <td>${attachment.fileName}</td>
			</tr>
			<tr>
				<th>Date</th>
				<td>${attachment.dateFr}</td>
			</tr>
			<c:if test="${not empty attachment.notes}">
				<tr>
					<th>Notes</th>
					<td>${attachment.notes}</td>
				</tr>
			</c:if>
    </table>
  </fieldset>
</body>
</html>
