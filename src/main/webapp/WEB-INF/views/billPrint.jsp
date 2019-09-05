<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />
<body>

	<br /><br />
	<br /><br /><br />
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
			<table>
				<tbody>
				<tr>
					<th scope="row">Rental Property Address(es): </th>
					<td>${rental.rentalAddresses}</td>
				</tr>
				<c:if test="${bill.hasMultiCount()}">
					<tr>
						<th scope="row">Number Of Multi Buildings:</th>
						<td>${bill.multiBuildingCnt}</td>
					</tr>
				</c:if>
				<c:if test="${bill.hasSingleCount()}">
					<tr>
						<th scope="row">Number Of Single Family Houses:</th>
						<td>${bill.singleBuildingCnt}</td>
					</tr>
				</c:if>
				<c:if test="${bill.hasBathCount()}">
					<tr>
						<th scope="row">Number Of Rooming Houses:</th>
						<td>${bill.roomingBuildingCnt}</td>
					</tr>
					<tr>
						<th scope="row">Number Of Bathrooms:</th>
						<td>${bill.bathCnt}</td>
					</tr>					
				</c:if>				
				<c:if test="${bill.hasCondoCount()}">
					<tr>
						<th scope="row">Number Of Condo Buildings:</th>
						<td>${bill.condoBuildingCnt}</td>
					</tr>
				</c:if>
				<c:if test="${bill.hasMultiCount() || bill.hasCondoCount()}">				
					<tr>
						<th scope="row">Number of Units:</th>
						<td>${bill.unitCnt}</td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<br />
			<b>Assessment</b>
			<br />
			<table border="1">
				<tbody>
					<tr>
						<td>
							<table border="0">
								<tbody>
									<c:if test="${empty bill.appeal}">
										<tr>
											<th scope="row">Inspection Fee:</th>
											<td align="right"><fmt:formatNumber type="currency" value="${bill.inspectionFee}" /></td>
										</tr>
										<c:if test="${bill.hasReinspFine()}">
											<tr>
												<th scope="row">Reinspection Fee:</th>
												<td align="right"><fmt:formatNumber type="currency" value="${bill.reinspFine}" /></td>
												<td>${bill.reinspDates}</td>
											</tr>
										</c:if>
										<c:if test="${bill.hasNoshowFine()}">				
											<tr>
												<th scope="row">No Show Fee:</th>
												<td align="right"><fmt:formatNumber type="currency" value="${bill.noshowFine}" /></td>
												<td>${bill.noshowDates}</td>
											</tr>
										</c:if>
										<c:if test="${bill.hasSummaryFine()}">
											<tr>
												<th scope="row">Failure to Timely Provide Summary of Rights & Responsibilities:</th>
												<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.summaryFine}" /></td>
												<td>&nbsp;</td>
											</tr>
										</c:if>
										<c:if test="${bill.hasIdlFine()}">
											<tr>
												<th scope="row">Failure to Timely Provide Inventory & Damage List:</th>
												<td valign="bottom" align="right"><fmt:formatNumber type="currency" value="${bill.idlFine}" /></td>
												<td></td>
											</tr>
										</c:if>
										<c:if test="${bill.hasCredit()}">
											<tr>
												<th scope="row">Credits:</th>
												<td align="right"><fmt:formatNumber type="currency" value="${bill.credit}" /></td>
												<td></td>
											</tr>
										</c:if>
										<c:if test="${bill.hasBhqaFine()}">				
											<tr>
												<th scope="row">Fines:</th>
												<td align="right"><fmt:formatNumber type="currency" value="${bill.bhqaFine}" /></td>
												<td>&nbsp;</td>
											</tr>
										</c:if>
										<s:if test="${bill.hasOtherFee()}">
											<tr>
												<td align="right">${bill.otherFeeTitle}</td>
												<td align="right">
													${bill.otherFee}
												</td>
											</tr>
										</s:if>
										<s:if test="${bill.hasOtherFee2()}">
											<tr>
												<td align="right">${bill.otherFee2Title}</td>
												<td align="right">
													${bill.otherFee2}
												</td>
											</tr>
										</s:if>										
									</c:if>
									<c:if test="${not empty bill.appeal}">
										<tr>
											<th scope="row">APPEALS:</th>
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
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
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
