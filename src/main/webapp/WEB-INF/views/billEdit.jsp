<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}billUpdate" method="post" modelAttribute="bill">
		<form:hidden path="rental.id" />
		<form:hidden path="id" />				
  <fieldset>
    <legend>Edit Bill</legend>
		<ul>
			<li> <a href="${uri}view/${bill.rental.id}">Related Rental ${bill.rental.id}</a></li>			
		</ul>				
    <table border="1">
			<caption>Standard Fees</caption>
			<tr>
        <th>Single Family House Rate:</th>
        <td>
          $<form:input path="singleBuildingRate" />  
        </td>
        <th>Condo Building Rate:</th>
        <td>
          $<form:input path="condoBuildingRate" /> 
        </td>
			</tr>
			<tr>
        <th>Multi Unit Building Rate:</th>
        <td>
          $<form:input path="multiBuildingRate" /> (apartments)
        </td>
        <th>Rooming Building Rate:</th>
        <td>
          $<form:input path="roomingBuildingRate" /> 
        </td>
			</tr>
			<tr>
        <th>Unit Rate:</th>
        <td>
          $<form:input path="unitRate" /> 
        </td>					
        <th>Rooming House bath Rate:</th>
        <td>
          $<form:input path="bathRate" /> 
        </td>
			</tr>
			<tr>
        <th>Reinspection Fee:</th>
        <td>
          $<form:input path="reinspRate" /> 
        </td>					
        <th>No Show Fee:</th>
        <td>
          $<form:input path="noshowRate" /> 
        </td>
			</tr>
			<tr>
        <th>Appeal Fee:</th>
        <td>
          $<form:input path="appealFee" /> 
          </td>
        <th>Summary Failure Rate:</th>
        <td>
            $<form:input path="summaryRate" /> 
        </td>
			</tr>
			<tr>
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
          <th>Single Family Houses:</th>
          <td>
            <form:input path="singleBuildingCnt" size="3" /> 
          </td>
          <th>Condo Buildings:</th>
          <td>
            <form:input path="condoBuildingCnt" size="3" /> 
          </td>
				</tr>
				<tr>
          <th>Multi Buildings:</th>
          <td>
            <form:input path="multiBuildingCnt" size="3" /> 
          </td>
          <th>Rooming Houses:</th>
          <td>
            <form:input path="roomingBuildingCnt" size="3" /> 
          </td>
				</tr>
				<tr>					
          <th>Units:</th>
          <td>
            <form:input path="unitCnt" size="3" /> 
          </td>
          <th>Rooming baths:</th>
          <td>
            <form:input path="bathCnt" size="2" /> 
          </td>
				</tr>
				<tr>							
          <th>B.H.Q.A Fee:</th>
          <td>
            $<form:input path="bhqaFine" />
          </td>					
					<th>Credits:</th>
					<td>
            $<form:input path="credit" /> 
          </td>
				</tr>
				<tr>
          <th>Number of Reinspection:</th>
          <td>
            <form:input path="reinspCnt" size="2" /> 
          </td>
          <th>Number of no show</th>
          <td>
            <form:input path="noshowCnt" size="2" /> 
          </td>
				</tr>
				<tr>
          <th><form:checkbox path="idlFlag" value="y" /> 
						IDL Fees, Count:</th>
          <td>
            <form:input path="idlCnt" size="2" /> 
          </td>					
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
				<tr><td colspan="4">You can add other fees below, add fees title
					and the fees value below</td>
				</tr>
				<tr>
					<th>Other Fees Title:</th>
          <td colspan="2">
            <form:input path="otherFeeTitle" size="30" /> 
          </td>
					<td>
             Fees $<form:input path="otherFee" />
					</td>
				</tr>
				<tr>
					<th>Another Fees Title:</th>
          <td colspan="2">
            <form:input path="otherFee2Title" size="30" /> 
          </td>
					<td>
            Fees $<form:input path="otherFee2" />
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
        <td><button type="submit">Save Changes</button></td>
				<td>
					<input type="button" onclick="window.location='${uri}billPrint/${bill.id}'" value="Printable" />
				</td>					
				<c:if test="${bill.isUnpaid()}">
					<td>
						<input type="button" onclick="window.location='${uri}receiptNew/${bill.id}'" value="New Receipt" />
					</td>
				</c:if>
      </tr>
    </table>
  </fieldset>		
  </form:form>
	<c:if test="${bill.hasReceipts()}">
		<jsp:include page="receipts.jsp" />
	</c:if>
	<br />
	<br />
</body>
</html>
