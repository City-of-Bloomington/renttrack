<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
  <fieldset>
    <legend>New Pull Reason </legend>
    <form:form action="${uri}settings/pullReasonSave" method="post" modelAttribute="pullReason">
      <table>
        <tr>
          <th>Reason</th>
          <td>
            <form:input path="reason" size="30" /> 
            <form:errors path="reason" cssClass="error" />
          </td>
				</tr>
        <tr>
          <th>Alias</th>
          <td>
            <form:input path="alias" size="10" /> 
            <form:errors path="alias" cssClass="error" />
          </td>
				</tr>				
				<tr>
          <td><button type="submit">Submit</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
  <br />
</body>
</html>
