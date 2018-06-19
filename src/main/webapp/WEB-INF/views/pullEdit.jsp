<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="header.jsp" />
<form:form action="${uri}pullUpdate" method="post" modelAttribute="pull">
	<form:hidden path="id" />
	<c:if test="${not empty pull.user}">			
		<form:hidden path="user.id" />
	</c:if>
	<form:hidden path="rental.id" />			
  <fieldset>
    <legend>Pull History</legend>
      <table>
				<tr>
					<th>ID</th>
					<td>${id}</td>
				</tr>
				<tr>
					<th>Related Rental</th>
					<td>
						<a href="${uri}${pull.rental.id}"> <c:out value="${pull.rental.id}" /></a>
					</td>
				</tr>				
				<tr>
          <th>Pull Reason</th>
          <td>
            <form:select path="pullReason.id">
							<form:option value="0" label="Pick Pull Reason" />
							<form:options items="${reasons}" itemValue="id" itemLabel="reason" />
						</form:select>
          </td>
				</tr>
				<tr>
          <th>Full Date</th>
          <td>
            <form:input path="dateFr" size="10" maxlength="10" /> 
            <form:errors path="dateFr" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Completed?</th>
          <td>
            <form:checkbox path="completed" value="y" />
          </td>
				</tr>				
				<tr>
          <th>Fulled By</th>
					<td><c:if test="${not empty pull.user}">${pull.user}</c:if></td>
          </td>
				</tr>
			</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
        <td><button type="submit">Save Changes</button></td>
      </tr>
    </table>
  </fieldset>		
</form:form>
<br />
<jsp:include page="footer.jsp" />

