<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="header.jsp" />
<form:form action="${uri}legalSave" method="post" modelAttribute="legal">
	<form:hidden path="rental_id" />
	<form:hidden path="startBy" />
	<form:hidden path="startDate" />			
  <fieldset>
    <legend>Start Legal</legend>
      <table class="vertaTable">
				<tr>
					<th>Rental ID</th>
					<td>${legal.rental_id}</td>
				</tr>
        <tr>
          <th>Case Type</th>
          <td>
            <form:select path="case_type" items="${legalTypes}" itemValue="id" itemLabel="name" /> 
            <form:errors path="case_type" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Start Date</th>
          <td>${legal.startDate}</td>
				</tr>
				<tr>
          <th>Start By</th>
					<td>${legal.startBy}</td>
				</tr>
				<tr>
          <th>Attention</th>
					<td>${legal.attention}</td>
				</tr>
				<tr>
					<th valign="top">Reason</th>
					<td>
						<textarea name="reason" cols="70" rows="3">
             ${legal.reason}
						</textarea>
					</td>
				</tr>
				<tr><td colspan="2" style="text-align:center">If there are multiple owners or addresses you may uncheck the ones that do not apply</td></tr>
				<c:if test="${legal.hasOwners()}">
					<c:forEach items="${legal.owners}" var="owner">
						<tr>
							<th>Owner</th>
							<td>
								<input type="checkbox" name="owner_ids" value="${owner.id}" checked="checked"/> ${owner}
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${legal.hasAddresses()}" >
					<c:forEach items="${legal.addresses}" var="addr">
						<tr>
							<th>Address</th>
							<td>
								<input type="checkbox" name="address_ids" value="${addr.id}" checked="checked"/> ${addr}
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
				<td>
					<button type="submit">Save</button>
				</td>
      </tr>
    </table>
  </fieldset>		
</form:form>
<br />
</body>
</html>
