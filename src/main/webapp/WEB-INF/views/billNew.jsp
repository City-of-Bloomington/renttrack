<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}billSave" method="post" modelAttribute="bill">
		<form:hidden path="rental.id" />			
		<fieldset>
    <legend>New Rental Bill</legend>
    <table border="1">
			<caption>Standard Fees</caption>
			<tr>
        <th>Building Rate:</th>
        <td>
          $<form:input path="buildingRate" /> 
        </td>
        <th>Unit Rate:</th>
        <td>
          $<form:input path="unitRate" /> 
        </td>					
			</tr>
			<tr>
        <th>Room/bath Rate:</th>
        <td>
          $<form:input path="bathRate" /> 
        </td>
        <th>Reinspection Fee:</th>
        <td>
          $<form:input path="reinspRate" /> 
        </td>					
			</tr>
			<tr>
        <th>No Show Fee:</th>
        <td>
          $<form:input path="noshowRate" /> 
        </td>
        <th>Appeal Fee:</th>
        <td>
          $<form:input path="appealFee" /> 
          </td>
			</tr>
			<tr>
        <th>Summary Failure Rate:</th>
        <td>
            $<form:input path="summaryRate" /> 
        </td>
        <th>IDL Rate:</th>
        <td>
          $<form:input path="idlRate" /> 
        </td>					
				</tr>
		</table>
		<br />
		<table border="1">
			<caption>Rental Total Fees</caption>
			<tr>
        <th>Property Type:</th>
        <td colspan="2">${bill.propertyTypes} 
					(if this is not correct fix it in the building page)
          </td>
					<td>
            <form:checkbox path="appeal" value="y" /><b> Appeal Bill</b>
          </td>
				</tr>
				<tr>
          <th>Buildings:</th>
          <td>
            <form:input path="buildingCnt" size="3" /> 
          </td>
          <th>Units:</th>
          <td>
            <form:input path="unitCnt" size="3" /> 
          </td>
				</tr>				
				<tr>
          <th>Rooms/bath:</th>
          <td>
            <form:input path="bathCnt" size="2" /> 
          </td>
          <th>B.H.Q.A Fee:</th>
          <td>
            $<form:input path="bhqaFine" />
          </td>					
					
				</tr>
				<tr>
					<th>Credits:</th>
					<td>
            $<form:input path="credit" /> 
          </td>					
          <th>Number of Reinspection:</th>
          <td>
            <form:input path="reinspCnt" size="2" /> 
          </td>
				</tr>
				<tr>
          <th>Number of no show</th>
          <td>
            <form:input path="noshowCnt" size="2" /> 
          </td>
          <th><form:checkbox path="idlFlag" value="y" /> 
						IDL Fees, Count:</th>
          <td>
            <form:input path="idlCnt" size="2" /> 
          </td>					
				</tr>
				<tr>
          <th><form:checkbox path="summaryFlag" value="y" /> 
						Summary Failure Fees, Count:</th>
          <td>
            <form:input path="summaryCnt" size="2" /> 
          </td>
				</tr>
				<tr>
          <th>Dates of Noshow:</th>
          <td colspan="3">
            <form:input path="noshowDates" size="40" /> 
          </td>
				</tr>
				<tr>
					<th>Dates of Reinspection:</th>
          <td colspan="3">
            <form:input path="reinspDates" size="40" /> 
          </td>
				</tr>
				<tr>
          <th>Bill Issue Date:</th>
          <td>
            <form:input path="issueDateFr" size="10" cssClass="date"/> 
          </td>
          <th>Bill Due Date:</th>
          <td>
            <form:input path="dueDateFr" size="10" cssClass="date" /> 
          </td>
				</tr>
				<tr>
          <th>Inspection Fee:</th>
          <td>
            $${bill.inspectionFee} 
          </td>
					<th>Total</th>
					<td>$${bill.total}</td>
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
	<br />
	<br />
</body>
</html>
