<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table>
	<caption>Legal-It Email History</caption>
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Date</th>
			<th scope="col">From Email</th>
			<th scope="col">To Email</th>
			<th scope="col">Subject</th>
			<th scope="col">Email Msg</th>
			<th scope="col">Email Error</th>
		</tr>
	</thead>
	<tbody>
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
	</tbody>
</table>

