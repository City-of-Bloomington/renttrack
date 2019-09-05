<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Inspection Template Components</legend>
	<table>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Building Num</th>
				<th scope="col">Unit Num</th>
				<th scope="col">Floor</th>
				<th scope="col">Component</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${template.templateComponents}" var="one">
				<tr>				
					<td>${one.id}</td>
					<td>${one.buildingNum+1}</td>
					<td>${one.unitNum+1}</td>					
					<td>${one.floorNum+1}</td>
					<td>${one.component}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</fieldset>	
