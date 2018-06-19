<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}receiptUpdate" method="post" modelAttribute="receipt">
		<form:hidden path="bill.id" />
		<form:hidden path="id" />
		<form:hidden path="receiptNo" />				
		<fieldset>
			<legend>Edit Receipt ${receipt.id}</legend>
      <table class="vertaTable">
				<tr>
          <th>Receipt No.:</th>
					<td>${receipt.receiptNo}</td>
				</tr>
				<tr>
          <th>Related Bill:</th>
					<td>
						<a href="${uri}billView/${receipt.bill.id}">${receipt.bill.id}</a>
					</td>
				</tr>
				<tr>
          <th>Balance Due:</th>
          <td>
            $${receipt.bill.amountDue}
          </td>
				</tr>				
				<tr>
          <th>Received Sum:</th>
          <td>
            $<form:input path="receivedSum" size="8" /> 
          </td>
				</tr>
				<tr>
          <th>Received Date:</th>
          <td>
            <form:input path="receivedDateFr" cssClass="date" /> 
          </td>
				</tr>
				<tr>
          <th>Received From:</th>
          <td>
            <form:input path="receivedFrom" size="30" /> 
          </td>
				</tr>				
				<tr>
          <th>Paid By:</th>
          <td>
            <form:radiobuttons path="paidBy" items="${payMethods}" /> 
          </td>
				</tr>
				<tr>
					<th>Check #:</th>
					<td>
            <form:input path="checkNo" size="20" /> 
          </td>					
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<table class="submitTable">
				<tr>
					<td>
						<input type="button" value="Printable" onclick="window.location='${uri}receiptPrint/${receipt.id}'" />
					</td>
          <td>
						<button type="submit">Save Changes</button></td>
        </tr>
      </table>
		</fieldset>			
  </form:form>
	<jsp:include page="footer.jsp" />

