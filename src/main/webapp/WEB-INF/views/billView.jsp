<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>Bill ${bill.id}</legend>
      <table border="1">
				<caption>Standard Fees</caption>
				<tr>
					<th>Related Rental</th>
					<td>${bill.rental.id}</td>
				</tr>
				<tr>
          <th>Building Rate:</th>
          <td>
            $${bill.buildingRate}  
          </td>
          <th>Unit Rate:</th>
          <td>
            $${bill.unitRate} 
          </td>					
				</tr>
				<tr>
          <th>Room/bath Rate:</th>
          <td>
            $${bill.bathRate}
          </td>
          <th>Reinspection Fee:</th>
          <td>
            $${bill.reinspRate} 
          </td>					
				</tr>
				<tr>
          <th>No Show Fee:</th>
          <td>
            $${bill.noshowRate} 
          </td>
          <th>Appeal Fee:</th>
          <td>
            $${bill.appealFee} 
          </td>
				</tr>
				<tr>
          <th>Summary Failure Rate:</th>
          <td>
            $${bill.summaryRate} 
          </td>
          <th>IDL Rate:</th>
          <td>
            $${bill.idlRate} 
          </td>					
				</tr>
			</table>
			<br />
			<table border="1">
				<caption>This Rental Specific Fees</caption>
				<c:if test="not empty bill.appeal">
					<tr>
						<td>
							<b> Bill Appealed</b>
						</td>
					</tr>
				</c:if>
				<tr>
          <th>Buildings:</th>
          <td>
            ${bill.buildingCnt} 
          </td>
          <th>Units:</th>
          <td>
            ${bill.unitCnt}
          </td>
				</tr>				
				<tr>
          <th>Rooms/bath:</th>
          <td>
            ${bill.bathCnt} 
          </td>
          <th>B.H.Q.A Fee:</th>
          <td>
            $${bill.bhqaFine}
          </td>					
				</tr>
				<tr>
					<th>Credits:</th>
					<td>
            $${bill.credit}
          </td>					
          <th>Number of Reinspection:</th>
          <td>
            ${bill.reinspCnt}
          </td>
				</tr>
				<tr>
          <th>Number of no show</th>
          <td>
            ${bill.noshowCnt} 
          </td>
          <th>IDL Count:</th>
          <td>
            ${bill.idlCnt}
          </td>					
				</tr>
				<tr>
          <th>Summary Failure Count:</th>
          <td>
            ${bill.summaryCnt} 
          </td>
					<th>Status</th>
					<td>${bill.status}</td>
				</tr>
				<tr>
          <th>Dates of Noshow:</th>
          <td colspan="3">
            ${bill.noshowDates} 
          </td>
				</tr>
				<tr>
					<th>Dates of Reinspection:</th>
          <td colspan="3">
            ${bill.reinspDates}
          </td>
				</tr>
				<tr>
          <th>Bill Issue Date:</th>
          <td>
            ${bill.issueDateFr}
          </td>
          <th>Bill Due Date:</th>
          <td>
            ${bill.dueDateFr}
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
				<c:if test="${bill.isUnpaid()}">				
					<tr>
						<td colspan="2"></td>
						<th>Balance</th>
						<td>
							$${bill.balance} 
						</td>
					</tr>
				</c:if>
			</table>
	</fieldset>
	<c:if test="${bill.isUnpaid()}">
		<fieldset>
			<table class="submitTable">
				<tr>
					<td>
						<input type="button" onlick="window.location='${uri}billEdit/${bill.id}'" value="Edit" />
					</td>
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
	</c:if>
	<c:if test="${bill.hasReceipts()}">
		<jsp:include page="receipts.jsp" />
	</c:if>
</body>
</html>
