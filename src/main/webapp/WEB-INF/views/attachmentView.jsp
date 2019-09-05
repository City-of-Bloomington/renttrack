<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Attachment</legend>
		<ul>
			<li>Id: ${attachment.id}</li>
			<c:if test="${not empty attachment.inspectionId}">
				<li>Related Inspection: ${attachement.inspectionId}</li>
			</c:if>
			<c:if test="${not empty attachment.rental.id}">
				<li>Related Rental: ${attachment.rental.id}</li>
			</c:if>			
			<li>Old File Name: ${attachment.oldFileName}</li>
			<li>File Name: ${attachment.fileName}</li>
			<li>Date: ${attachment.dateFr}</li>
			<c:if test="${not empty attachment.notes}">
				<li>Notes: ${attachment.notes}</li>
			</c:if>
		</ul>
  </fieldset>
</body>
</html>
