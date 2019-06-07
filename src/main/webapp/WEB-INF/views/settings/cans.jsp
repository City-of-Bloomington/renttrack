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
    <legend>Inspection Cans</legend>
		<a href="${uri}settings/canNew">New Can</a>
    <table class="outTable">
      <tr>
				<th>ID</th>
				<th>Title/File Ref</th>
				<th>Text Type</th>
				<th>First Paragraph</th>
				<th>Other Paragraphs/List</th>
				<th>Other Actions </th>
			</tr>
			<c:if test="${not empty cans}">
				<c:forEach items="${cans}" var="one">
					<tr>				
						<td><a href="${uri}settings/can/${one.id}">Edit ${one.id}</a></td>
						<td>${one.title}</td>
						<td>${one.type}</td>
						<td>${one.item1}</td>
						<td>${one.other}</td>
						<td><a href="${uri}settings/canDelete/${one.id}">Delete ${one.id}</a></td>						
					</tr>
				</c:forEach>
			</c:if>
    </table>
  </fieldset>

</body>
</html>
