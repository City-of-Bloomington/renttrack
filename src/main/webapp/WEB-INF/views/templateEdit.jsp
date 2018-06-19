<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
	<form:form action="${uri}templateComponentDelete" method="post" modelAttribute="template">
		<form:hidden path="id" />
		<fieldset>
			<legend>Inspection Template ${template.id}</legend>
			<table class="vertaTable">
				<tr>
					<th>Related Rental:</th>
					<td><a href="${uri}view/${template.rentalId}"> ${template.rentalId}</a></td>
				</tr>
				<tr><th>Date</th><td>${template.dateFr}</td></tr>
				<tr><th>User</th><td>${template.user}</td></tr>
			</table>
		</fieldset>		
		<c:if test="${template.hasTemplateComponents()}">
			<fieldset>
				<legend>Inspection Template Components</legend>
				<details>
					<summary>Instructions</summary>
					<ul>
						<li>If you think some of the components are irrelevant you can delete </li>
						<li>To Delete a component: check the component(s) checkbox(es) (the first column) and then click on 'Delete' button. You can delete more than one component at a time.</li>
						<li>To Update a component title: click on the 'Component' column link, a popup window will open where you can change the component title</li>
						<li>To add new components: click on 'Add New Component' button</li>
					</ul>
				</details>
				<table class="outTable">
					<tr>
						<th>check to delete</th>
						<th>Building #</th>
						<th>Unit #</th>
						<th>Floor</th>
						<th>Edit Component</th>
					</tr>
					<c:forEach items="${template.templateComponents}" var="one">
						<tr>				
							<td><input type="checkbox" name="del_ids" value="${one.id}" /></td>
							<td>${one.buildingNum+1}</td>
							<td>${one.unitNum+1}</td>					
							<td>${one.floorNum+1}</td>
							<td><a href="#" onclick="return popwit('${uri}componentEdit/${one.id}','EditComponent');"> ${one.component}</a></td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
			<fieldset>
				<div class="menu">
					<ul class="menu">
						<li class="menu">
							&nbsp;
						</li>						
						<li class="menu">
							<button type="submit">Delete</button></th>
						</li>
						<c:if test="${template.buildingCnt > 1}">
							<c:forEach begin="0" end="${template.buildingCnt-1}" varStatus="loop">
								<li class="menu">
									<button onclick="return popwit('${uri}componentNew/${template.id}/${loop.index}','AddComponent');"> Add New Component (Building ${loop.count})</button>
								</li>
							</c:forEach>
						</c:if>
						<c:if test="${template.buildingCnt == 1}">
							<li class="menu">
								<button onclick="return popwit('${uri}componentNew/${template.id}/0','AddComponent');"> Add New Component</button>
							</li>
						</c:if>						
					</ul>
				</div>
			</fieldset>			
		</c:if>
	</form:form>
	<br />
	<br />
<jsp:include page="footer.jsp" />

