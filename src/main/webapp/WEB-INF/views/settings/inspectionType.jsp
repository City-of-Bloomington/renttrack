<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<body>
  <fieldset>
    <legend>New Inspection Type</legend>
    <form:form action="${uri}settings/inspectionTypeSave" method="post" modelAttribute="inspectionType">
      <table>
        <tr>
          <th>Name</th>
          <td>
            <form:input path="name" /> 
            <form:errors path="name" cssClass="error" />
          </td>
					
				</tr>
        <tr>
          <th>Alias</th>
          <td>
            <form:input path="alias" /> (short name)
            <form:errors path="alias" cssClass="error" />
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
