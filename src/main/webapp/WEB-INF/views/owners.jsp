<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
  <fieldset>
    <legend>Owners</legend>
		<a href="${uri}owner">New Owner</a>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">Phones</th>
					<th scope="col">Address</th>
					<th scope="col">City, State Zip</th>
					<th scope="col">Email</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${owners}" var="owner">
					<tr>				
						<td><a href="${uri}owner/${owner.id}">${owner.id}</a></td>
						<td>${owner.name}</td>					
						<td>${owner.phonesInfo}</td>
						<td>${owner.address}</td>
						<td>${owner.cityStateZip}</td>
						<td>${owner.email}</td>
						<td>
							<a href="${uri}ownerDelete/${owner.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>

</body>
</html>
