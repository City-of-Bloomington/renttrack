<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<body>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
  <fieldset>
    <legend>New User</legend>
    <form:form action="${uri}userSave" method="post" modelAttribute="user">
      <table>
        <tr>
          <th>Username</th>
          <td>
            <form:input path="username" /> 
            <form:errors path="username" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Full Name</th>
          <td>
            <form:input path="full_name" /> 
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
          <td><button type="submit">Save</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>

</body>
</html>
