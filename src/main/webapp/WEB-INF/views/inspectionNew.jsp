<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}inspectionSave" method="post" modelAttribute="inspection">
		<form:hidden path="rental.id" />	
  <fieldset>
    <legend>New Inspection</legend>
		<input type="hidden" name="timeStatus" value="In Progress" />			
    <table class="vertaTable">
			<tr>
				<th>Related Rental </th>
				<td><a href="${uri}view/${inspection.rental.id}"> ${inspection.rental.id}</a></td>
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
					<td>In Progress</td>
				</tr>
		</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
        <td><button type="submit">Save</button></td>
      </tr>
    </table>
  </fieldset>		
  </form:form>
	<jsp:include page="footer.jsp" />	

