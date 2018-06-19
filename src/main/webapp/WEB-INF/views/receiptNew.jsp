<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}receiptSave" method="post" modelAttribute="receipt">
		<form:hidden path="bill.id" />			
  <fieldset>
    <legend>New Receipt</legend>
    <table class="vertaTable">
			<tr>
        <th>Related Bill:</th>
        <td><a href="${uri}billView/${bill.id}">${bill.id}</a>
        </td>
			</tr>
			<tr>
				<th>Balance Due:</th>
				<td>
					<c:if test="${bill.isUnpaid()}">									
						$${bill.balance}
					</c:if>
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
        <td><button type="submit">Save</button></td>
      </tr>
		</table>
  </fieldset>		
  </form:form>
	<jsp:include page="footer.jsp" />

