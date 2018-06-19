<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
  <fieldset>
    <legend>Receipts</legend>
    <table class="wideTable">
      <tr>
				<th>ID</th>
				<th>Receipt #</th>
				<th>Amount Received</th>
				<th>Date</th>
				<th>Received from</th>
				<th>Paid by</th>
				<th>Check #</th>
			</tr>
      <c:forEach items="${bill.receipts}" var="receipt">
				<tr>				
					<td>
						<c:if test="${receipt.isNewRecord()}">
							<a href="${uri}receiptEdit/${receipt.id}">Edit ${receipt.id}</a>
						</c:if>
						<c:if test="${!receipt.isNewRecord()}">
							${receipt.id}
						</c:if>
					</td>
					<td>${receipt.receiptNo}</td>					
					<td>${receipt.receivedSum}</td>
					<td>${receipt.receivedDateFr}</td>
					<td>${receipt.receivedFrom}</td>
					<td>${receipt.paidBy}</td>
					<td>${receipt.checkNo}</td>
				</tr>
      </c:forEach>
    </table>
  </fieldset>

</body>
</html>
