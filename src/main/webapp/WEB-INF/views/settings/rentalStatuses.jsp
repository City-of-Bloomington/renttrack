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
    <legend>Rental Statuses</legend>
		<a href="${uri}settings/newRentalStatus">New Rental Status</a>
    <table class="outTable">
      <tr>
				<th>ID</th>
				<th>Alias</th>
				<th>Name</th>
			</tr>
      <c:forEach items="${rentalStatuses}" var="prop">
				<tr>				
					<td><a href="${uri}settings/rentalStatus/${prop.id}">Edit ${prop.id}</a></td>
					<td>${prop.alias}</td>					
					<td>${prop.name}</td>
				</tr>
      </c:forEach>
    </table>
  </fieldset>

</body>
</html>
