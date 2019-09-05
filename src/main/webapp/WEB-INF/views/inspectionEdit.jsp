<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>		
  <form:form action="${uri}inspectionUpdate" method="post" modelAttribute="inspection">
		<form:hidden path="rental.id" />
		<form:hidden path="id" />			
		<form:hidden path="timeStatus" />
		<form:hidden path="inspectFile" />
		<fieldset>
			<legend>Edit Inspection</legend>
			<div class="form-group">
				<fieldset>
					<div>
						<label>Related Rental </label>
						<a href="${uri}view/${inspection.rental.id}"> ${inspection.rental.id}</a>
					</div>
					<div>
          <label>Inspection Type</label>
            <form:select path="inspectionType.id" items="${inspectionTypes}" itemValue="id" itemLabel="name" /> 
					</div>
					<div>
						<label>Inspection By</label>
            <form:select path="inspector.id" items="${inspectors}" itemValue="id" itemLabel="fullName" /> 
					</div>
					<div class="form-field">
						<label>Inspection Date</label>
						<form:input path="inspectionDateFr" cssClass="date" />
					</div>
					<div class="form-field">
						<label>Compliance Date</label>
						<form:input path="complianceDateFr" cssClass="date" />
					</div>
					<div class="form-field">
						<label>Accessory</label>
            <form:input path="accessory" size="30" />
					</div>
					<div class="form-field">
					<th>Violations</th>
          <form:input path="violations" size="2" />
					</div>
					<div class="form-field">
					<label>Smoke Detectors #</label>
            <form:input path="smokeDetectors" size="2" />
					</div>
					<div class="form-field">
						<label>Life Safery #</label>
            <form:input path="lifeSafety" size="2" />
					</div>
					<div class="form-field">
					<label>Time Spent</label>
            <form:input path="timeSpent" size="5" />(hh.mm) 
					</div>
					<div class="form-field">
						<label>Time Status: </label>
						${inspection.timeStatus}
					</div>
					<c:if test="${not empty inspection.inspectFile}">
						<div>
							<label>Inspection File:</label>
							<a href="${uri}inspectDownload/${inspection.id}">Download ${inspection.inspectFile}</a>
						</div>
					</c:if>
					<div>
						<form:checkbox path="cancelled" value="y" />Cancel this inspection
					</div>
				</fieldset>
				<div>
					<div>
						<button type="submit" class="button">Save Changes</button>
						<c:if test="${inspection.canBeApproved()}">
							<input type="button" value="Approve Inspection" onclick="window.location='${uri}inspectionApprove/${inspection.id}'" class="button"/>
					</c:if>
						<input type="button" value="New Attachement" onclick="window.location='${uri}attachmentNew/inspection/${inspection.id}'" class="button"/>
					<c:if test="${!inspction.hasFile()}">
						<button onclick="window.location='${uri}inspectionCanNew/${inspection.id}';return false;" class="button">Add Cans</button>
						<c:if test="${inspection.hasInspectionCans()}">
							<button onclick="window.location='${uri}inspectionCreateFile/${inspection.id}';return false;" class="button">Generate Inspction File</button>
						</c:if>
					</c:if>						
					</div>
				</div>
			</div>
		</fieldset>
  </form:form>
	<c:if test="${inspection.hasInspectionCans()}">
		<jsp:include page="inspectionCans.jsp" />	
	</c:if>
	<c:if test="${inspection.hasAttachments()}">
		<c:set var="attachements" value="${inspection.attachments}" scope="request"/>
		<jsp:include page="attachments.jsp" />	
	</c:if>		
	<jsp:include page="footer.jsp" />	

