<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<form:form action="${uri}varianceUpdate" method="post" modelAttribute="variance">
	<form:hidden path="id" />
	<form:hidden path="rental.id" />
	<c:if test="${variance.hasUser()}">
		<form:hidden path="user.id" />
	</c:if>	
  <fieldset>
    <legend>Edit Variance</legend>
    <table class="vertaTable">
			<tr>
				<th>ID</th>
				<td><c:out value="${variance.id}" /></td>
			</tr>
			<tr>
				<th>Related Rental</th>
				<td>${variance.rental.id}</td>
			</tr>								
			<tr>
        <th>Date</th>
        <td>				
					<form:input path="dateFr" /> 
          <form:errors path="date" cssClass="error" />
        </td>
			</tr>
			<tr>
        <th>Variance Text</th>
        <td>
          <form:textarea path="variance" cols="50" rows="5" /> 
            <form:errors path="variance" cssClass="error" />
					</td>
				</tr>
				<c:if test="${variance.hasUser()}">
					<tr>
						<th>User</th>
						<td>${variance.user}</td>
					</tr>
				</c:if>
		</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
        <td>
					<button type="submit">Save Changes</button>
				</td>
				<td>
					<input type="button" value="Delete" onclick="window.location='${uri}varianceDelete/${variance.id}'" />		
				</td>
      </tr>
    </table>
  </fieldset>		
</form:form>
<br />

</body>
</html>
