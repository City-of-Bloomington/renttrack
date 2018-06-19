<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>View Inspection</legend>
    <table class="vertaTable">					
    <tr>
      <th>ID </th>
			<td>
				<c:if test="${inspection.canBeApproved()}">
					<a href="${uri}inspectEdit/${inspection.id}">Edit ${inspection.id}</a>
				</c:if>
				<c:if test="${!inspection.canBeApproved()}">
					${inspection.id}
				</c:if>					
			</td>
		</tr>				
    <tr>
      <th>Related Rental </th>
      <td><a href="${uri}view/${inspection.rental.id}"> ${inspection.rental.id}</a></td>
		</tr>
			<tr>
        <th>Inspection Type</th>
        <td>
          ${inspection.inspectionType}
        </td>
			</tr>
			<tr>
        <th>Inspection By</th>
        <td>${inspection.inspector}</td>
			</tr>				
			<tr>
        <th>Inspection Date</th>
				<td>${inspection.inspectionDateFr}  </td>
			</tr>
			<tr>
        <th>Compliance Date</th>
				<td>${inspection.complianceDateFr}</td>
			</tr>
			<c:if test="${not empty inspection.inspectFile}">
				<tr>
					<th>Inspection File</th>
					<td><a href="${uri}inspectDownload/${inspection.id}">Download ${inspection.inspectFile}</a></td>
				</tr>
			</c:if>
			<tr>
				<th>Other features</th>
        <td>
          ${inspection.features}
        </td>
			</tr>
			<c:if test="${inspection.wasCancelled()}">
				<tr>
					<th>Inspection:</th>
					<td>Cancelled </td>
				</tr>
			</c:if>			
			<c:if test="${inspection.wasApproved()}">
				<tr>
					<th>Approved:</th>
					<td>by: ${inspection.approver}, on: ${inspection.approvedDateFr}</td>
				</tr>
			</c:if>
		</table>
	</fieldset>
	<fieldset>
		<c:if test="${inspection.canBeApproved()}">
			<form:form action="${uri}inspectionUpload" method="post" enctype="multipart/form-data" >
				<input type="hidden" name="id" value="${inspection.id}" />						
				<table class="vertaTable">
				<tr><td colspan="2">You can upload a replacement for the current inspection file, use the upload button below</td>
				</tr>
				<tr>
					<th>Upload File</th>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="submit" name="submit">Submit Upload</button>
					</td>
				</tr>
			</table>
			</form:form>
		</c:if>
	</fieldset>
	<c:if test="${inspection.canBeApproved()}">
		<fieldset>
		<div class="menu">
			<ul class="menu">
				<li class="menu">			
						<input type="button" value="Approve Inspection" onclick="window.location='${uri}inspectionApprove/${inspection.id}'" />
				</li>
				<li class="menu">							
						<input type="button" value="New Attachement" onclick="window.location='${uri}attachementNew/inspection/${inspection.id}'" />
				</li>
					<c:if test="${!inspction.hasFile()}">
						<li class="menu">			
							<button onclick="window.location='${uri}inspectionCanNew/${inspection.id}';">Add Cans</button>
						</li>
						<c:if test="${inspection.hasInspectionCans()}">
						<li class="menu">			
								<button onclick="window.location='${uri}inspectionCreateFile/${inspection.id}';">Generate Inspction File</button>
						</li>
						</c:if>
					</c:if>
			</ul>
		</div>
		</fieldset>
		<c:if test="${inspection.hasInspectionCans()}">
			<jsp:include page="inspectionCans.jsp" />	
		</c:if>		
	</c:if>
	<c:if test="${inspection.hasAttachements()}">
		<c:set var="attachements" value="${inspection.attachements}" scope="request"/>
		<jsp:include page="attachements.jsp" />	
	</c:if>	

	<jsp:include page="footer.jsp" />	

