<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Owner</legend>
    <table class="vertaTable">
      <tr>
				<th class="vertaTable">Id</th>
				<td><a href="${uri}ownerEdit/${owner.id}">Edit ${owner.id}</a></td>
			</tr>
			<tr>
				<th>Name</th>
        <td>${owner.name}</td>
			</tr>
			<tr>
				<th>Address</th>
        <td>${owner.address}</td>
			</tr>
			<tr>
				<th>City, State Zipcode</th>
        <td>${owner.cityStateZip}</td>
			</tr>
			<tr>
				<th>Email</th>
        <td>${owner.email}</td>
			</tr>
			<c:if test="${not empty owner.notes}">
				<tr>
					<th>Notes</th>
					<td>${owner.notes}</td>
				</tr>
			</c:if>
			<c:if test="${owner.hasPhones()}" >
				<tr>
					<td colspan="2">
						<jsp:include page="phones.jsp" />
					</td>
				</tr>
			</c:if>
			<c:if test="${owner.hasOwnerRentals()}">
				<tr>
					<td colspan="2">
						<jsp:include page="ownerRentals.jsp" />	
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<input type="button" value="Add Phone" onclick="window.location='${uri}newPhone/${owner.id}'" />
					<input type="button" value="Delete" onclick="window.location='${uri}ownerDelete/${owner.id}'" />
					<input type="button" value="New Owner" onclick="window.location='${uri}owner'" />							
				</td>
      </tr>
    </table>
  </fieldset>
</body>
</html>
