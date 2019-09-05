<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>Bill ${bill.id}</legend>
		<ul>
			<li> <a href="${uri}view/${bill.rental.id}">Related Rental ${bill.rental.id}</a></li>			
		</ul>		
    <table border="1">
			<caption>Standard Fees</caption>
			<tbody>
				<tr>
					<th scope="row">Related Rental</th>
					<td>${bill.rental.id}</td>
				</tr>
				<tr>
					<th scope="row">Single Family House Rate:</th>
					<td>
						$${bill.singleBuildingRate}  
					</td>
					<th scope="row">Multi Building Rate:</th>
					<td>
						$${bill.multiBuildingRate}  
					</td>
				</tr>
				<tr>
					<th scope="row">Condo Building Rate:</th>
					<td>
						$${bill.condoBuildingRate}  
					</td>
					<th scope="row">Rooming Building Rate:</th>
					<td>
						$${bill.roomingBuildingRate}  
					</td>
				</tr>
				<tr>					
					<th scopre="row">Unit Rate:</th>
					<td>
						$${bill.unitRate} 
					</td>					
					<th scope="row">Rooming House per bathroom Rate:</th>
					<td>
          $${bill.bathRate}
					</td>
				</tr>
				<tr>
					<th scope="row">Reinspection Fee:</th>
					<td>
						$${bill.reinspRate} 
					</td>					
					<th scope="row">No Show Fee:</th>
					<td>
						$${bill.noshowRate} 
					</td>
				</tr>
				<tr>
          <th scope="row">Appeal Fee:</th>
          <td>
            $${bill.appealFee} 
          </td>
				</tr>
				<tr>
          <th scope="row">Summary Failure Rate:</th>
          <td>
            $${bill.summaryRate} 
          </td>
          <th scope="row">IDL Rate:</th>
          <td>
            $${bill.idlRate} 
          </td>					
				</tr>
			</tbody>
		</table>
		<br />
		<table border="1">
			<caption>This Rental Specific Fees</caption>
			<tbody>
				<c:if test="not empty bill.appeal">
					<tr>
						<td>
							<b> Bill Appealed</b>
						</td>
					</tr>
				</c:if>
				<tr>
          <th scope="row">Single Family House #:</th>
          <td>
            ${bill.singleBuildingCnt} 
          </td>
          <th scope="row">Multi Building #:</th>
          <td>
            ${bill.multiBuildingCnt} 
          </td>
				</tr>				
				<tr>					
          <th scope="row">Condo Building #:</th>
          <td>
            ${bill.condoBuildingCnt} 
          </td>
          <th scope="row">Rooming House #:</th>
          <td>
            ${bill.roomingBuildingCnt} 
          </td>
				</tr>				
				<tr>							
          <th scope="row">Units:</th>
          <td>
            ${bill.unitCnt}
          </td>
				</tr>				
				<tr>
          <th scope="row">Rooming baths:</th>
          <td>
            ${bill.bathCnt} 
          </td>
          <th scope="row">B.H.Q.A Fee:</th>
          <td>
            $${bill.bhqaFine}
          </td>					
				</tr>
				<tr>
					<th scope="row">Credits:</th>
					<td>
            $${bill.credit}
          </td>					
          <th scope="row">Number of Reinspection:</th>
          <td>
            ${bill.reinspCnt}
          </td>
				</tr>
				<tr>
          <th scope="row">Number of no show</th>
          <td>
            ${bill.noshowCnt} 
          </td>
          <th scope="row">IDL Count:</th>
          <td>
            ${bill.idlCnt}
          </td>					
				</tr>
				<tr>
          <th scope="row">Summary Failure Count:</th>
          <td>
            ${bill.summaryCnt} 
          </td>
					<th scope="row">Status</th>
					<td>${bill.status}</td>
				</tr>
				<tr>
          <th scope="row">Dates of Noshow:</th>
          <td colspan="3">
            ${bill.noshowDates} 
          </td>
				</tr>
				<tr>
					<th scope="row">Dates of Reinspection:</th>
          <td colspan="3">
            ${bill.reinspDates}
          </td>
				</tr>
				<c:if test="${bill.hasOtherFee()}">
					<tr>
						<td colspan="2">${bill.otherFeeTitle}</td>
						<td>
							$${bill.otherFee}
						</td>
					</tr>
				</c:if>
				<c:if test="${bill.hasOtherFee2()}">
					<tr>
						<td colspan="2">${bill.otherFee2Title}</td>
						<td>
							$${bill.otherFee2}
						</td>
					</tr>
				</c:if>				
				<tr>
          <th scope="row">Bill Issue Date:</th>
          <td>
            ${bill.issueDateFr}
          </td>
          <th scope="row">Bill Due Date:</th>
          <td>
            ${bill.dueDateFr}
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
				<c:if test="${bill.isUnpaid()}">				
					<tr>
						<td colspan="2"></td>
						<th scope="row">Balance</th>
						<td>
							$${bill.balance} 
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</fieldset>
	<c:if test="${bill.isUnpaid()}">
		<fieldset>
			<table>
				<tbody>
					<tr>
						<td>
							<input type="button" onclick="window.location='${uri}billEdit/${bill.id}'" value="Edit" />
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
				</tbody>
			</table>
		</fieldset>
	</c:if>
	<c:if test="${bill.hasReceipts()}">
		<jsp:include page="receipts.jsp" />
	</c:if>
</body>
</html>
