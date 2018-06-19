<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Inspection Template Components</legend>
	<table class="outTable">
		<tr>
			<th>ID</th>
			<th>Building Num</th>
			<th>Unit Num</th>
			<th>Floor</th>
			<th>Component</th>
		</tr>
		<c:forEach items="${template.templateComponents}" var="one">
			<tr>				
				<td>${one.id}</td>
				<td>${one.buildingNum+1}</td>
				<td>${one.unitNum+1}</td>					
				<td>${one.floorNum+1}</td>
				<td>${one.component}</td>
			</tr>
		</c:forEach>
	</table>
</fieldset>	
