<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
  <fieldset>
    <legend>Email Log Details</legend>
		<table class="vertaTable">
			<tr>
				<th>Log ID</th>
				<td>${emailLog.id}</td>
			</tr>
			<tr>
				<th>Date</th>
				<td>${emailLog.dateFr}</td>
			</tr>
			<tr>
				<th>Email From</th>
				<td>${emailLog.emailFrom}</td>
			</tr>
			<tr>
				<th>User Initiated</th>
				<td>${emailLog.user}</td>
			</tr>
			<tr>
				<th>Category</th>
				<td>${emailLog.type}</td>
			</tr>
		</table>
		<br />
		<c:if test="${emailLog.emailDetailLogs.size() > 0}">
			Note: if an email did not go through an error text will be shown in the 'Status' column stating the reason.<br /><br />
			<table class="wideTable">
				<caption>Total emails in this run ${emailLog.emailDetailLogs.size()}</caption>
				<tr>
					<th>to</th>
					<th>cc</th>
					<th>bcc</th>
					<th>Owner IDs</th>
					<th>Agent IDs</th>
					<th>Rental IDs</th>
					<th>Status?</th>
				</tr>
				<c:forEach items="${emailLog.emailDetailLogs}" var="log">
					<tr>
						<td>${log.logToClean}</td>									
						<td>${log.logCcClean}</td>					
						<td>${log.logBccClean}</td>
						<td>${log.ownersId}</td>
						<td>${log.agentId}</td>
						<td>${log.rentalsId}</td>
						<td>
							<c:if test="${not empty log.errorText}">${log.errorText}</c:if>
							<c:if test="${empty log.errorText}">Success</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
  </fieldset>

</body>
</html>
