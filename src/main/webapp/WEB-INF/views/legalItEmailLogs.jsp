<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table class="wideTable">
	<caption>Legal-It Email History</caption>
  <tr>
		<th>ID</th>
		<th>Date</th>
		<th>From Email</th>
		<th>To Email</th>
		<th>Subject</th>
		<th>Email Msg</th>
		<th>Email Error</th>
	</tr>
  <c:forEach items="${legalItEmailLogs}" var="one" varStatus="row">
		<c:if test="${row.index < 5}">
			<tr>
				<td>${one.id}</td>
				<td>${one.dateFr}</td>
				<td>${one.EFrom}</td>
				<td>${one.ETo}</td>
				<td>${one.ESubject}</td>
				<td>${one.EMsg}</td>
				<td><c:if test="${not empty one.errorText}">${one.errorText}</c:if></td>
			</tr>
		</c:if>
  </c:forEach>
</table>

