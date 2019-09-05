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
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Receipt #</th>
					<th scope="col">Amount Received</th>
					<th scope="col">Date</th>
					<th scope="col">Received from</th>
					<th scope="col">Paid by</th>
					<th scope="col">Check #</th>
				</tr>
			</thead>
			<tbody>
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
			</tbody>
    </table>
  </fieldset>
</body>
</html>
