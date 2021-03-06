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
		<ul>
			<li> <a href="${uri}view/${bill.rental.id}">Related Rental ${bill.rental.id}</a></li>			
		</ul>		
    <table border="1">
			<caption>Standard Fees</caption>
			<tbody>
				<tr>
					<th scope="row">Single Family House Rate:</th>
					<td>
						$<form:input path="singleBuildingRate" />  
					</td>
					<th scope="row">Condo Building Rate:</th>
					<td>
						$<form:input path="condoBuildingRate" /> 
					</td>
				</tr>
				<tr>
					<th scope="row">Multi Unit Building Rate:</th>
					<td>
						$<form:input path="multiBuildingRate" /> (apartments)
					</td>
					<th scope="row">Rooming Building Rate:</th>
					<td>
						$<form:input path="roomingBuildingRate" /> 
					</td>
				</tr>
				<tr>
					<th scope="row">Unit Rate:</th>
					<td>
						$<form:input path="unitRate" /> 
					</td>					
					<th scope="row">Rooming House bath Rate:</th>
					<td>
						$<form:input path="bathRate" /> 
					</td>
				</tr>
				<tr>
					<th scope="row">Reinspection Fee:</th>
					<td>
						$<form:input path="reinspRate" /> 
					</td>					
					<th scope="row">No Show Fee:</th>
					<td>
						$<form:input path="noshowRate" /> 
					</td>
				</tr>
				<tr>
					<th scope="row">Appeal Fee:</th>
					<td>
						$<form:input path="appealFee" /> 
          </td>
					<th scope="row">Summary Failure Rate:</th>
					<td>
            $<form:input path="summaryRate" /> 
					</td>
				</tr>
				<tr>
					<th scope="row">IDL Rate:</th>
					<td>
						$<form:input path="idlRate" /> 
					</td>					
				</tr>
			</tbody>
		</table>
		<br />
		<table border="1">
			<caption>Rental Total Fees</caption>
			<tbody>
				<tr>
					<th scope="row">Property Type:</th>
					<td colspan="2">${bill.propertyTypes} 
						(if this is not correct fix it in the building page)
          </td>
					<td>
            <form:checkbox path="appeal" value="y" /><b> Appeal Bill</b>
          </td>
				</tr>
				<tr>
          <th scope="row">Single Family Houses:</th>
          <td>
            <form:input path="singleBuildingCnt" size="3" /> 
          </td>
          <th scope="row">Condo Buildings:</th>
          <td>
            <form:input path="condoBuildingCnt" size="3" /> 
          </td>
				</tr>
				<tr>
          <th scope="row">Multi Buildings:</th>
          <td>
            <form:input path="multiBuildingCnt" size="3" /> 
          </td>
          <th scope="row">Rooming Houses:</th>
          <td>
            <form:input path="roomingBuildingCnt" size="3" /> 
          </td>
				</tr>
				<tr>					
          <th scope="row">Units:</th>
          <td>
            <form:input path="unitCnt" size="3" /> 
          </td>
          <th scope="row">Rooming baths:</th>
          <td>
            <form:input path="bathCnt" size="2" /> 
          </td>
				</tr>
				<tr>							
          <th scope="row">B.H.Q.A Fee:</th>
          <td>
            $<form:input path="bhqaFine" />
          </td>					
					<th scope="row">Credits:</th>
					<td>
            $<form:input path="credit" /> 
          </td>
				</tr>
				<tr>
          <th scope="row">Number of Reinspection:</th>
          <td>
            <form:input path="reinspCnt" size="2" /> 
          </td>
          <th scope="row">Number of no show</th>
          <td>
            <form:input path="noshowCnt" size="2" /> 
          </td>
				</tr>
				<tr>
          <th scope="row"><form:checkbox path="idlFlag" value="y" /> 
						IDL Fees, Count:</th>
          <td>
            <form:input path="idlCnt" size="2" /> 
          </td>					
          <th scope="row"><form:checkbox path="summaryFlag" value="y" /> 
						Summary Failure Fees, Count:</th>
          <td>
            <form:input path="summaryCnt" size="2" /> 
          </td>
				</tr>
				<tr>
          <th scope="row">Dates of Noshow:</th>
          <td colspan="3">
            <form:input path="noshowDates" size="40" /> 
          </td>
				</tr>
				<tr>
					<th scope="row">Dates of Reinspection:</th>
          <td colspan="3">
            <form:input path="reinspDates" size="40" /> 
          </td>
				</tr>
				<tr><td colspan="4">You can add other fees below, add fees title
					and the fees value below</td>
				</tr>
				<tr>
					<th scope="row">Other Fees Title:</th>
          <td colspan="2">
            <form:input path="otherFeeTitle" size="40" /> 
          </td>
					<td>
             Fees $<form:input path="otherFee" />
					</td>
				</tr>
				<tr>
					<th scope="row">Another Fees Title:</th>
          <td colspan="2">
            <form:input path="otherFee2Title" size="40" /> 
          </td>
					<td>
            Fees $<form:input path="otherFee2" />
					</td>
				</tr>				
				<tr>
          <th scope="row">Bill Issue Date:</th>
          <td>
            <form:input path="issueDateFr" size="10" cssClass="date"/> 
          </td>
          <th scope="row">Bill Due Date:</th>
          <td>
            <form:input path="dueDateFr" size="10" cssClass="date" /> 
          </td>
				</tr>
				<tr>
          <th scope="row">Inspection Fee:</th>
          <td>
            $${bill.inspectionFee} 
          </td>
					<th scope="row">Total</th>
					<td>$${bill.total}</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<fieldset>
			<table>
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
