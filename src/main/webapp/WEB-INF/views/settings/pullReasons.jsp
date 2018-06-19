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
    <legend>Pull Reasons</legend>
		<a href="${uri}settings/newPullReason">New Pull Reason</a>
    <table class="outTable">
      <tr>
				<th>ID</th>
				<th>Alias</th>
				<th>Reason</th>
			</tr>
      <c:forEach items="${pullReasons}" var="one">
				<tr>				
					<td><a href="${uri}settings/pullReason/${one.id}">Edit ${one.id}</a></td>
					<td>${one.alias}</td>					
					<td>${one.reason}</td>
				</tr>
      </c:forEach>
    </table>
  </fieldset>

</body>
</html>
