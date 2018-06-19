<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="../header.jsp" />		
  <fieldset>
    <legend>Update Pull Reason</legend>
    <form:form action="${uri}settings/pullReasonUpdate" method="post" modelAttribute="pullReason">
			<form:hidden path="id" /> 
      <table>
				<tr>
					<th>ID</th>
					<td>${pullReason.id}</td>
        <tr>
          <th>Reason</th>
          <td>
            <form:input path="reason" size="30" maxlength="30" /> 
            <form:errors path="reason" cssClass="error" />
          </td>
				</tr>
        <tr>
          <th>Alias</th>
          <td>
            <form:input path="alias" size="10" maxlength="30" /> 
            <form:errors path="alias" cssClass="error" />
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
