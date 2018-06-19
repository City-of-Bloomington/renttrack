<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Variance</legend>
    <table class="vertaTable">
      <tr>
				<th>Id</th>
				<td><a href="${uri}varianceEdit/${variance.id}">Edit ${variance.id}</a></td>
			</tr>
			<tr>
				<th>Related Rental</th>
        <td><a href="${uri}view/${variance.rental.id}">${variance.rental.id}</td>
			</tr>
			<tr>
				<th>Date</th>
        <td>${variance.dateFr}</td>
			</tr>			
			<tr>
				<th>Variance Text</th>
				<td>${variance.variance}</td>
			</tr>
			<c:if test="${not empty variance.user}">
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
					<input type="button" value="Delete" onclick="window.location='${uri}varianceDelete/${variance.id}'" />										
				</td>
      </tr>
    </table>
  </fieldset>
	<br />
</body>
</html>
