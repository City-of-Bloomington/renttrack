<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="outTable">
	<caption>Attachments</caption>
  <tr>
		<th>File</th>
		<th>Date</th>
		<th>Notes</th>
	</tr>
  <c:forEach items="${attachments}" var="one">
		<tr>				
			<td><a href="${uri}attachDownload/${one.id}">Download ${one.oldFileName}</a></td>
			<td>${one.dateFr}</td>
			<td>${one.notes}</td>
		</tr>
  </c:forEach>
</table>

