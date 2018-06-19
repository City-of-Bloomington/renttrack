<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
  <fieldset>
    <legend>New User</legend>
    <form:form action="${uri}settings/buildingTypeSave" method="post" modelAttribute="buildingType">
      <table>
        <tr>
          <th>Name</th>
          <td>
            <form:input path="name" /> 
            <form:errors path="name" cssClass="error" />
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
