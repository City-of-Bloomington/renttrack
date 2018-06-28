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
    <legend>Standard Billing Fees</legend>
		<a href="${uri}settings/standardFeesNew">New Billing Standard Fees</a>
    <table class="outTable">
      <tr>
				<th>ID</th>
				<th>Date</th>
				<th>User</th>
			</tr>
			<c:if test="${not empty standardFeeses}">
				<c:forEach items="${standardFeeses}" var="one">
					<tr>				
						<td><a href="${uri}settings/standardFees/${one.id}">View or Edit ${one.id}</a></td>
						<td>${one.dateFr}</td>
						<td>${one.user}</td>
					</tr>
				</c:forEach>
			</c:if>
    </table>
  </fieldset>

</body>
</html>
