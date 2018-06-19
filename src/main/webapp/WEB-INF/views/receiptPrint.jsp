<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />
<body>
	<center>
		<br /><br /><br /><br /><br />
		<br /><br />
		<fieldset>
		<b>RENTAL RECEIPT</b><br />
		<b>No. ${receipt.receiptNo}</b>
			<br /><br />
			<table border="0">
				<tr>
					<td align="right"><b>Received Date: </b></td>
					<td>${receipt.receivedDateFr}</td>
				</tr>
				<tr>
					<td align="right"><b>Received From: </b></td>
					<td>${receipt.receivedFrom}</td>
				</tr>
				<tr>
					<td align="right"><b>For Property Located at: </b></td>
					<td>${bill.rental.rentalAddresses}</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="right"><b>Amount Received: </b></td>
					<td align="right"><fmt:formatNumber type="currency" value="${receipt.receivedSum}" /></td>
				</tr>
				<c:if test="${empty bill.appeal}">
					<tr>
						<td align="right"><b>Inspection Fee:</b></td>
						<td align="right"><fmt:formatNumber type="currency" value="${bill.inspectionFee}" /></td>
					</tr>
					<c:if test="${bill.hasReinspFine()}">
						<tr>
							<td align="right"><b>Reinspection Fee:</b></td>
							<td align="right"><fmt:formatNumber type="currency" value="${bill.reinspFine}" /></td>
						</tr>
					</c:if>
					<c:if test="${bill.hasNoshowFine()}">				
						<tr><td align="right"><b>No Show Fee:</b></td>
							<td align="right"><fmt:formatNumber type="currency" value="${bill.noshowFine}" /></td>
						</tr>
					</c:if>
					<c:if test="${bill.hasSummaryFine()}">
						<tr>
							<td align="right"><b>Failure to Timely Provide Summary of Rights & Responsibilities:</b></td>
							<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.summaryFine}" /></td>
						</tr>
					</c:if>
					<c:if test="${bill.hasIdlFine()}">
						<tr>
							<td align="right"><b>Failure to Timely Provide Inventory & Damage List:</b></td>
							<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.idlFine}" /></td>
						</tr>
					</c:if>
					<c:if test="${bill.hasCredit()}">
						<tr>
							<td align="right"><b>Credits:</b></td>
							<td align="right"><fmt:formatNumber type="currency" value="${bill.credit}" /></td>
						</tr>
					</c:if>
					<c:if test="${bill.hasBhqaFine()}">				
						<tr>
							<td align="right"><b>B.H.A.Q Fines:</b></td>
							<td align="right"><fmt:formatNumber type="currency" value="${bill.bhqaFine}" /></td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${not empty bill.appeal}">
					<tr>
						<td align="right"><b>Appeal Fee:</b></td>
						<td align="right"><fmt:formatNumber type="currency" value="${bill.appealFee}" /></td>
					</tr>
				</c:if>
				<c:if test="${bill.isUnpaid()}">
					<tr>
						<td align="right"><b>Remaining Balance: </b></td>
						<td align="right">
							<fmt:formatNumber type="currency" value="${bill.balance}" /></td>
					</tr>
				</c:if>
				<tr>
					<td align="right"><b>Paid by: </b></td>
					<td>${receipt.paidBy} </td>
				</tr>
				<c:if test="${not empty receipt.checkNo}">
					<tr>
						<td align="right">Check No.: </td>
						<td>${receipt.checkNo}</td>
					</tr>
				</c:if>
				<tr>
					<td align="right"><b>Building/Units: </b></td>
					<td>
						${bill.buildingCnt}/${bill.unitCnt}
					</td>
				</tr>
				<c:if test="${bill.hasBathCount()}">
					<tr>
						<td align="right"><b>Rooming House Baths: </b></td>
						<td>							
							${bill.bathCnt}
						</td>
					</tr>
				</c:if>
			</table>
			<br /><br />
			<b>Approved by the State Board of Accounts, 2004.</b>
			<br /><br />
			<font size="+1">Thank you for your payment</font>
			<br />
		</fieldset>
	</center>
</body>
</html>
