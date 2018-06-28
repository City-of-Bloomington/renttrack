<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
  <fieldset>
    <legend>BMC Building Egresses</legend>
		<a href="${uri}settings/egressNew">New Egress</a>
    <table class="outTable">
      <tr>
				<th>ID</th>
				<th>Start - End Years</th>
				<th>Type</th>
				<th>Width</th>				
				<th>Height</th>
				<th>Sill Height</th>
				<th>Area</th>
				<th>Area (other floors)</th>
			</tr>
			<c:if test="${not empty egresses}">
				<c:forEach items="${egresses}" var="one">
					<tr>				
						<td><a href="${uri}settings/egress/${one.id}">Edit ${one.id}</a></td>
						<td>${one.startEndYear}</td>
						<td>${one.type}</td>
						<td>${one.width}</td>						
						<td>${one.height}</td>
						<td>${one.sillHeight}</td>
						<td>${one.area}</td>
						<td>${one.area2}</td>
					</tr>
				</c:forEach>
			</c:if>
    </table>
  </fieldset>

</body>
</html>
