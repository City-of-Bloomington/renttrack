<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
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
      <table>
        <tr>
          <th>Related Rental </th>
          <td><a href="${uri}view/${inspection.rental.id}"> ${inspection.rental.id}</a>
					</td>
				</tr>
				<tr>
          <th>Inspection Type</th>
          <td>
            <form:select path="inspectionType.id" items="${inspectionTypes}" itemValue="id" itemLabel="name" /> 
          </td>
				</tr>
				<tr>
          <th>Inspection By</th>
          <td>
            <form:select path="inspector.id" items="${inspectors}" itemValue="id" itemLabel="fullName" /> 
          </td>
				</tr>				
				<tr>
          <th>Inspection Date</th>
					<td><form:input path="inspectionDateFr" cssClass="date" />  </td>
				</tr>
				<tr>
          <th>Compliance Date</th>
					<td><form:input path="complianceDateFr" cssClass="date" />  </td>
				</tr>				
				<tr>
					<th>Accessory</th>
          <td>
            <form:input path="accessory" size="30" />
          </td>
				</tr>
				<tr>
					<th>Violations</th>
          <td>
            <form:input path="violations" size="2" />
          </td>
				</tr>
				<tr>
					<th>Smoke Detectors #</th>
          <td>
            <form:input path="smokeDetectors" size="2" />
          </td>
				</tr>
				<tr>
					<th>Life Safery #</th>
          <td>
            <form:input path="lifeSafety" size="2" />
          </td>
				</tr>
				<tr>
					<th>Time Spent</th>
          <td>
            <form:input path="timeSpent" size="5" />(hh.mm) 
          </td>
				</tr>
				<tr>
					<th>Time Status</th>
					<td>${inspection.timeStatus}</td>
				</tr>
				<c:if test="${not empty inspection.inspectFile}">
					<tr>
						<th>Inspection File</th>
						<td><a href="${uri}inspectDownload/${inspection.id}">Download ${inspection.inspectFile}</a></td>
					</tr>
				</c:if>
				<tr>
					<th> </th>
          <td>
            <form:checkbox path="cancelled" value="y" />Cancel this inspection
          </td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
		<div class="menu">
			<ul class="menu">
				<li class="menu">					
					<button type="submit">Save Changes</button>
				</li>
					<c:if test="${inspection.canBeApproved()}">
						<li class="menu">			
							<input type="button" value="Approve Inspection" onclick="window.location='${uri}inspectionApprove/${inspection.id}'" />
						</li>
					</c:if>
					<li class="menu">			
						<input type="button" value="New Attachement" onclick="window.location='${uri}attachmentNew/inspection/${inspection.id}'" />
					</li>
					<c:if test="${!inspction.hasFile()}">
						<li class="menu">			
							<button onclick="window.location='${uri}inspectionCanNew/${inspection.id}';return false;">Add Cans</button>
						</li>
						<c:if test="${inspection.hasInspectionCans()}">
							<li class="menu">			
								<button onclick="window.location='${uri}inspectionCreateFile/${inspection.id}';return false;">Generate Inspction File</button>
							</li>
						</c:if>
					</c:if>						
			</ul>
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

