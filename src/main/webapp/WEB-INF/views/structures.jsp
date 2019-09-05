<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Buildings</legend>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Identifier</th>				
					<th scope="col">Building Type</th>
					<th scope="col">Property Type</th>
					<th scope="col">Year Built</th>
					<th scope="col">Egress Info?</th>
					<th scope="col">Units</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rental.rentalStructures}" var="structure">
					<tr>				
						<td><a href="${uri}structure/${structure.id}">${structure.id}</a></td>
						<td>${structure.identifier}</td>					
						<td>${structure.buildingType}</td>
						<td>${structure.propertyType}</td>
						<td>${structure.yearBuilt}</td>					
						<td><c:if test="${structure.hasEgressInfo()}">Yes</c:if>
							<c:if test="${!structure.hasEgressInfo()}">No</c:if></td>
							<td>${structure.unitsCount}</td>
					</tr>
					<c:if test="${structure.hasUnits()}">
						<c:set var="structure" value="${structure}" scope="request"/>					
						<tr>
							<td colspan="5">
								<jsp:include page="units.jsp" />								
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>

