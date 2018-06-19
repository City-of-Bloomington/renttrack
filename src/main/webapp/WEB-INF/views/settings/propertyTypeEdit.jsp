<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="../header.jsp" />		
  <fieldset>
    <legend>Update Property Type</legend>
    <form:form action="${uri}settings/propertyTypeUpdate" method="post" modelAttribute="propertyType">
			<form:hidden path="id" /> 
      <table>
				<tr>
					<th>ID</th>
					<td>${propertyType.id}</td>
        <tr>
          <th>Name</th>
          <td>
            <form:input path="name" size="30" maxlength="30" /> 
            <form:errors path="name" cssClass="error" />
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
