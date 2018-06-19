<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Inspection Cans</legend>
    <table class="wideTable">
      <tr>
				<th>ID</th>
				<th>Unit</th>
				<th>Component</th>
				<th>Second Title</th>
				<th>Text Type</th>
				<th>First Paragraph</th>
				<th>Other Contents</th>
				<th>Action</th>
			</tr>
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
    </table>
  </fieldset>

