<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Inspection Cans</legend>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Unit</th>
					<th scope="col">Component</th>
					<th scope="col">Second Title</th>
					<th scope="col">Text Type</th>
					<th scope="col">First Paragraph</th>
					<th scope="col">Other Contents</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty inspection.inspectionCans}">
					<c:forEach items="${inspection.inspectionCans}" var="one">
						<tr>				
							<td><a href="${uri}inspectionCan/${one.id}">Edit ${one.id}</a></td>
							<td>${one.templateComponent.unitNum+1}</td>						
							<td>${one.templateComponent.component}</td>
							<td>${one.title}</td>
							<td>${one.type}</td>
							<td>${one.item1}</td>
							<td>${one.other}</td>
							<td><a href="${uri}inspectionCanRemove/${one.id}">Remove ${one.id}</a></td>						
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
    </table>
  </fieldset>

