<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />	
  <fieldset>
    <legend>User</legend>
    <table>
      <tr>
				<td>Id</td>
				<td><a href="${uri}userEdit/${user.id}">Edit ${user.id}</a></td>
			</tr>
			<tr>
				<th>Username</th>
        <td>${user.username}</td>
			</tr>
			<tr>
				<th>Full Name</th>
        <td>${user.full_name}</td>
			</tr>
			<tr>
				<th>Role</th>
        <td>${user.role}</td>
			</tr>			
			<tr>
				<th>Active?</th>
				<td>
					<c:if test="${user.isActive()}">Yes</c:if>
					<c:if test="${!user.isActive()}">No</c:if>
				</td>					
				</td>
      </tr>
    </table>
  </fieldset>
</body>
</html>
