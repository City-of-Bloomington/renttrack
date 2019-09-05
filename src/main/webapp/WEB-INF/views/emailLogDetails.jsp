<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
  <fieldset>
    <legend>Email Log Details</legend>
		<ul>
			<li>Log ID ${emailLog.id}</li>
			<li>Date ${emailLog.dateFr}</li>
			<li>Email From ${emailLog.emailFrom}</li>
			<li>User Initiated ${emailLog.user}</li>
			<li>Category ${emailLog.type}</li>
		</ul>
		<br />
		<c:if test="${emailLog.emailDetailLogs.size() > 0}">
			Note: if an email did not go through an error text will be shown in the 'Status' column stating the reason.<br /><br />
			<table>
				<caption>Total emails in this run ${emailLog.emailDetailLogs.size()}</caption>
				<thead>
				<tr>
					<th scope="col">to</th>
					<th scope="col">cc</th>
					<th scope="col">bcc</th>
					<th scope="col">Owner IDs</th>
					<th scope="col">Agent IDs</th>
					<th scope="col">Rental IDs</th>
					<th scope="col">Status?</th>
				</tr>
				</thead>
				<tbody>
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
				</tbody>
			</table>
		</c:if>
  </fieldset>

</body>
</html>
