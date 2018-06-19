<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<fieldset>
  <legend>Most Recent Email Logs</legend>
	<c:if test="${not empty emailLogs}">
		<table class="wideTable">
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Email From</th>
				<th>User Initiated</th>
				<th>Category</th>
			</tr>
			<c:forEach items="${emailLogs}" var="log">
				<tr>				
					<td><a href="${uri}emailLogDetails/${log.id}">More Details</a></td>
					<td>${log.dateFr}</td>					
					<td>${log.emailFrom}</td>
					<td><c:if test="${not empty log.user}">${log.user}</c:if>&nbsp;</td>
					<td>${log.type}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
  </fieldset>

