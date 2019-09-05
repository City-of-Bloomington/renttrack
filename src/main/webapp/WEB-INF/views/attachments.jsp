<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<caption class="sr-only">Attachments</caption>
	<thead>
		<tr>
			<th scope="col">File</th>
			<th scope="col">Date</th>
			<th scope="col">Notes</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${attachments}" var="one">
			<tr>				
				<td><a href="${uri}attachDownload/${one.id}">Download ${one.oldFileName}</a></td>
				<td>${one.dateFr}</td>
				<td>${one.notes}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

