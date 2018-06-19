<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />
<body>
	<fieldset>
		<center>
			<font face="helvetica" size="+1"><b>Billing Statement</b></font>
			<br /><br />
			<b>Date: </b>${bill.issueDateFr}
			<br /><br />
			<table border="0">
				<tr>
					<td valign="top"><b>Owner(s):</b></td>
					<td>
						<c:if test="${rental.hasOwners()}">						
							<c:forEach items="${rental.rentalOwners}" var="one">
								<font size="-1">
									${one.owner.name}<br />				
									${one.owner.address}<br />
									${one.owner.cityStateZip}<br />
								</font>
							</c:forEach>
						</c:if>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<c:if test="${rental.hasAgent()}">
					<tr>
						<td valign="top"><b>AGENT:</b></td>
						<td>
							<font size="-1">
								${rental.agent.name}<br />
								${rental.agent.address}<br />
								${rental.agent.cityStateZip}<br />
							</font>
						</td>
					</tr>
				</c:if>
			</table>
			<table border="0">
				<tr>
					<td><b>Rental Property Address(es): </b></td>
					<td>${rental.rentalAddresses}</td>
				</tr>
				<tr>
					<td align="right"><b>Number of Buildings:</b></td>
					<td>${bill.buildingCnt}</td>
				</tr>			
				<tr>
					<td align="right"><b>Number of Units:</b></td>
					<td>${bill.unitCnt}</td>
				</tr>
				<c:if test="${bill.hasBathCount()}">
					<tr>
						<td align="right"><b>Number Of Bathrooms:</b></td>
						<td>${bill.bathCnt}</td>
					</tr>
				</c:if>
			</table>
			<br />
			<b>Assessment</b>
			<br />
			<table border="1">
				<tr>
					<td>
						<table border="0">
							<c:if test="${empty bill.appeal}">
								<tr>
									<td align="right"><b>Inspection Fee:</b></td>
									<td align="right"><fmt:formatNumber type="currency" value="${bill.inspectionFee}" /></td>
								</tr>
								<c:if test="${bill.hasReinspFine()}">
									<tr>
										<td align="right"><b>Reinspection Fee:</b></td>
										<td align="right"><fmt:formatNumber type="currency" value="${bill.reinspFine}" /></td>
										<td>${bill.reinspDates}</td>
									</tr>
								</c:if>
								<c:if test="${bill.hasNoshowFine()}">				
									<tr><td align="right"><b>No Show Fee:</b></td>
										<td align="right"><fmt:formatNumber type="currency" value="${bill.noshowFine}" /></td>
										<td>${bill.noshowDates}</td>
									</tr>
								</c:if>
								<c:if test="${bill.hasSummaryFine()}">
									<tr>
										<td align="right"><b>Failure to Timely Provide Summary of Rights & Responsibilities:</b></td>
										<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.summaryFine}" /></td>
										<td>&nbsp;</td>
									</tr>
								</c:if>
								<c:if test="${bill.hasIdlFine()}">
									<tr>
										<td align="right"><b>Failure to Timely Provide Inventory & Damage List:</b></td>
										<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.idlFine}" /></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${bill.hasCredit()}">
									<tr>
										<td align="right"><b>Credits:</b></td>
										<td align="right"><fmt:formatNumber type="currency" value="${bill.credit}" /></td>
										<td></td>
									</tr>
								</c:if>
								<c:if test="${bill.hasBhqaFine()}">				
									<tr>
										<td align="right"><b>Fines:</b></td>
										<td align="right"><fmt:formatNumber type="currency" value="${bill.bhqaFine}" /></td>
										<td>&nbsp;</td>
									</tr>
								</c:if>
							</c:if>
							<c:if test="${not empty bill.appeal}">
								<tr>
									<td align="right"><b>APPEALS:</b></td>
									<td align="right"><fmt:formatNumber type="currency" value="${bill.appealFee}" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td><td>&nbsp;</td>
								</tr>
							</c:if>
							<tr><td>&nbsp;</td><td align="right">==========</td></tr>
							<tr>
								<td align="right"><b>Total Amount Due:</b></td>
								<td align="right"><b><fmt:formatNumber type="currency" value="${bill.total}" /></b></td>
							</tr>
							<tr>
								<td align="right"><b>Due By: </b></td>
								<td align="right">${bill.dueDateFr}</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</center>
		<br />
		<b>* RENTAL PERMIT WILL BE ISSUED UPON RECEIPT OF PAYMENT </b>
		<br />
		<p>
			Cash, check, money order and credit cards are acceptable payment types. Please make your check or money order payable to "HAND". 
				A copy of this statement must be returned with your payment within 30 days to: City of Bloomington, Housing and Neighborhood Development, P.O. Box 100, Bloomington, IN 47402.
		</p>
		<p>
			<b>If payment is not received within 30 days, any long-term occupancy permit will revert to a three-year permit, and this matter will be referred to the City Legal Department. You are responsible for all fees incurred regardless of whether you complete the entire inspection process or the property will no longer be used as a rental.
			</b>
		</p>
	</fieldset>
</body>
</html>
