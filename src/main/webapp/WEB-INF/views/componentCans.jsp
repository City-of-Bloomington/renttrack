<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<h3>Inspection Components & Cans</h3>
<c:if test="${not empty message}">
	<p>${message}</p>
</c:if>
<details>
	<summary>Inspection Info</summary>
	<fieldset>
		<table class="vertaTable">
			<tr>
				<th>Related Inspection </th>
				<td><a href="${uri}inspection/${inspection.id}"> ${inspection.id}</a></td>
			</tr>				
			<tr>
				<th>Related Rental </th>
				<td><a href="${uri}view/${inspection.rental.id}"> ${inspection.rental.id}</a></td>
			</tr>
			<tr>
				<th>Inspection Type</th>
				<td>
					${inspection.inspectionType}
				</td>
			</tr>
			<tr>
				<th>Inspection By</th>
				<td>${inspection.inspector}</td>
			</tr>				
			<tr>
				<th>Inspection Date</th>
				<td>${inspection.inspectionDateFr}  </td>
			</tr>
		</table>
	</fieldset>
</details>
<c:if test="${inspection.hasInspectionCans()}">
	<details>
		<summary>Components & Cans</summary>
		<jsp:include page="inspectionCans.jsp" />
	</details>
</c:if>
<details>
	<summary>Instructions</summary>
	<ul>
		<li>You can add one or more cans to each component</li>
		<li>You can add one can to each component at a time </li>
		<li>To add another can to a visited component click on 'Add Another Can' </li>
		<li>You can edit an already added can by clicking on 'Inspection Cans' in top and look for related component-can</li>
		<li>Click on the 'Add Can' next to the component</li>
	</ul>
</details>
<fieldset>
	<legend>Components</legend>
	<c:if test="${not empty inspection.inspectionTemplate}">
		<table class="vertaTable">
			<tr>
				<td><b>Component</b></td>
				<td><b>Action</b></td>
			</tr>
			<c:forEach items="${inspection.inspectionTemplate.templateComponents}" var="one" varStatus="row" >
				<tr>
					<td>${row.count} - ${one.component}</td>
					<td>
						<c:if test="${one.isVisited()}"> 
							(visited)
							<input type="button" onclick="window.location='${uri}inspectionCanAdd/${inspection.id}/${one.id}'" value="Add Another Can" />									
						</c:if>
						<c:if test="${!one.isVisited()}"> 
							<input type="button" onclick="window.location='${uri}inspectionCanAdd/${inspection.id}/${one.id}'" value="Add Can" />
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</fieldset>
<jsp:include page="footer.jsp" />
