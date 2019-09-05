<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table>
	<caption>Pull History</caption>
	<thead>
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Pull Date</th>
			<th scope="col">Pull Reason</th>
			<th scope="col">Pull By</th>
			<th scope="col">Completed?</th>
			<th scope="col">Completed Date</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${rental.pullHistories}" var="pull" varStatus="row">
			<c:if test="${row.index < 5}">
				<tr>				
					<td>
						<c:choose>
							<c:when test="${pull.done}">
								${pull.id}
						</c:when>
						<c:otherwise>
							<a href="${uri}pullEdit/${pull.id}">Edit ${pull.id}</a>
						</c:otherwise>
						</c:choose>
					</td>
					<td>${pull.dateFr}</td>
					<td>${pull.pullReason}</td>
					<td>${pull.user}</td>
					<td><c:if test="${pull.isDone()}">Yes</c:if></td>
					<td>${pull.completedDateFr}</td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>

