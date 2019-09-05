<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<fieldset>
  <legend>Most Recent Email Logs</legend>
	<c:if test="${not empty emailLogs}">
		<table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Date</th>
					<th scope="col">Email From</th>
					<th scope="col">User Initiated</th>
					<th scope="col">Category</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${emailLogs}" var="log">
					<tr>				
						<td><a href="${uri}emailLogDetails/${log.id}">More Details</a></td>
						<td>${log.dateFr}</td>					
						<td>${log.emailFrom}</td>
						<td><c:if test="${not empty log.user}">${log.user}</c:if>&nbsp;</td>
						<td>${log.type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
  </fieldset>

