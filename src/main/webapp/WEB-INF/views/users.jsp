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
    <legend>Users</legend>
		<a href="${uri}userNew">New User</a>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Username</th>
					<th scope="col">Full Name</th>
					<th scope="col">Role</th>
					<th scope="col">Active?</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>				
						<td><a href="${uri}user/${user.id}">${user.id}</a></td>
						<td>${user.username}</td>
						<td>${user.fullName}</td>
						<td>${user.role}</td>					
						<td>
							<c:if test="${user.isActive()}">Yes</c:if>
							<c:if test="${!user.isActive()}">No</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>
</body>
</html>
