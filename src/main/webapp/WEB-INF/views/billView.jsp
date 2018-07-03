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
					<td>Related Rental</td>
					<td>${bill.rental.id}</td>
				</tr>
				<tr>
          <td>Single Family House Rate:</td>
          <td>
            $${bill.singleBuildingRate}  
          </td>
          <td>Multi Building Rate:</td>
          <td>
            $${bill.multiBuildingRate}  
          </td>
				</tr>
				<tr>
          <td>Condo Building Rate:</td>
          <td>
            $${bill.condoBuildingRate}  
          </td>
          <td>Rooming Building Rate:</td>
          <td>
            $${bill.roomingBuildingRate}  
          </td>
				</tr>
				<tr>					
          <td>Unit Rate:</td>
          <td>
            $${bill.unitRate} 
          </td>					
          <td>Rooming House per bathroom Rate:</td>
          <td>
            $${bill.bathRate}
          </td>
				</tr>
				<tr>
          <td>Reinspection Fee:</td>
          <td>
            $${bill.reinspRate} 
          </td>					
          <td>No Show Fee:</td>
          <td>
            $${bill.noshowRate} 
          </td>
				</tr>
				<tr>
          <td>Appeal Fee:</td>
          <td>
            $${bill.appealFee} 
          </td>
				</tr>
				<tr>
          <td>Summary Failure Rate:</td>
          <td>
            $${bill.summaryRate} 
          </td>
          <td>IDL Rate:</td>
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
          <td>Single Family House #:</td>
          <td>
            ${bill.singleBuildingCnt} 
          </td>
          <td>Multi Building #:</td>
          <td>
            ${bill.multiBuildingCnt} 
          </td>
				</tr>				
				<tr>					
          <td>Condo Building #:</td>
          <td>
            ${bill.condoBuildingCnt} 
          </td>
          <td>Rooming House #:</td>
          <td>
            ${bill.roomingBuildingCnt} 
          </td>
				</tr>				
				<tr>							
          <td>Units:</td>
          <td>
            ${bill.unitCnt}
          </td>
				</tr>				
				<tr>
          <td>Rooming baths:</td>
          <td>
            ${bill.bathCnt} 
          </td>
          <td>B.H.Q.A Fee:</td>
          <td>
            $${bill.bhqaFine}
          </td>					
				</tr>
				<tr>
					<td>Credits:</td>
					<td>
            $${bill.credit}
          </td>					
          <td>Number of Reinspection:</td>
          <td>
            ${bill.reinspCnt}
          </td>
				</tr>
				<tr>
          <td>Number of no show</td>
          <td>
            ${bill.noshowCnt} 
          </td>
          <td>IDL Count:</td>
          <td>
            ${bill.idlCnt}
          </td>					
				</tr>
				<tr>
          <td>Summary Failure Count:</td>
          <td>
            ${bill.summaryCnt} 
          </td>
					<td>Status</td>
					<td>${bill.status}</td>
				</tr>
				<tr>
          <td>Dates of Noshow:</td>
          <td colspan="3">
            ${bill.noshowDates} 
          </td>
				</tr>
				<tr>
					<td>Dates of Reinspection:</td>
          <td colspan="3">
            ${bill.reinspDates}
          </td>
				</tr>
				<tr>
          <td>Bill Issue Date:</td>
          <td>
            ${bill.issueDateFr}
          </td>
          <td>Bill Due Date:</td>
          <td>
            ${bill.dueDateFr}
          </td>
				</tr>
				<tr>
          <td>Inspection Fee:</td>
          <td>
            $${bill.inspectionFee} 
          </td>
					<td>Total</td>
					<td>$${bill.total}</td>
				</tr>
				<c:if test="${bill.isUnpaid()}">				
					<tr>
						<td colspan="2"></td>
						<td>Balance</td>
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
