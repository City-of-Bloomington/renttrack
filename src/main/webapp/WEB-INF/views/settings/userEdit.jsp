<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="../header.jsp" />
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
  <fieldset>
    <legend>User</legend>
    <form:form action="${uri}userUpdate" method="post" modelAttribute="user">
			<form:hidden path="id" /> 
      <table>
				<tr>
					<th>ID</th>
					<td><c:out value="${user.id}" /></td>
        <tr>
          <th>Username</th>
          <td>
            <form:input path="username" size="10" maxlength="10" /> 
            <form:errors path="username" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Full Name</th>
          <td>
            <form:input path="full_name" size="30" maxlength="70" /> 
            <form:errors path="full_name" cssClass="error" />
          </td>
				</tr>
				<tr>
					<th>Role</th>
					<td>
            <form:select path="role">
							<form:option value="0" label="Pick Role" />
							<form:options items="${roles}" />
						</form:select>
					</td>
				</tr>
				<tr>
          <th>Inactive</th>
          <td>
            <form:checkbox path="inactive" value="y" /> 
            <form:errors path="inactive" cssClass="error" />
          </td>
				</tr>				
				<tr>
          <td><button type="submit">Save Changes</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
  <br />

</body>
</html>
