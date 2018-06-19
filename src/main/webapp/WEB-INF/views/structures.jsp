<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Buildings</legend>
    <table class="wideTable">
      <tr>
				<th width="10%">ID</th>
				<th width="20%">Identifier</th>				
				<th width="15%">Building Type</th>
				<th width="15%">Property Type</th>
				<th width="10%">Year Built</th>
				<th width="10%">Egress Info?</th>
				<th>Units</th>
			</tr>
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
    </table>
  </fieldset>

