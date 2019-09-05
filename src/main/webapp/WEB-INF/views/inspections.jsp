<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<fieldset>
  <legend>Inspections</legend>
  <table>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Date</th>
				<th scope="col">Inspection Type</th>
				<th scope="col">Inspector</th>
				<th scope="col">Compliance Date</th>
				<th scope="col">Inspection File</th>
				<th scope="col">Other Features</th>
				<th scope="col">Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${inspections}" var="one" varStatus="row" >
				<c:if test="${row.index < 5}">
					<tr>				
						<td><a href="${uri}inspection/${one.id}">${one.id}</a></td>
						<td>${one.inspectionDateFr}</td>					
						<td>${one.inspectionType}</td>
						<td>${one.inspector}</td>
						<td>${one.complianceDateFr}</td>
						<td>
							<c:if test="${not empty one.inspectFile}">
								<a href="${uri}inspectDownload/${one.id}"> ${one.inspectFile}</a>
							</c:if> &nbsp;
						</td>
						<td>${one.features}</td>
						<td>
							<c:if test="${one.wasCancelled()}">Cancelled</c:if>
							<c:if test="${one.wasApproved()}">Approved by ${one.approver}</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
  </table>
</fieldset>
