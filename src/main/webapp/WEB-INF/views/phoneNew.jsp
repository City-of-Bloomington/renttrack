<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>New Phone</legend>
    <form:form action="${uri}phoneSave" method="post" modelAttribute="phone">
			<form:hidden path="owner.id" />
      <table>
				<tr>
					<th>Owner</th>
					<td>
						<a href="${uri}owner/${phone.owner.id}">${phone.owner.name}</a>
					</td>
				</tr>
        <tr>
          <th>Phone #</th>
          <td>
            <form:input path="phoneNum" /> 
            <form:errors path="phoneNum" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Type</th>
          <td>
            <form:select path="type">
							<form:option value="0" label="Pick Type" />
							<form:options items="${phoneTypes}" />
						</form:select>
          </td>
				</tr>				
				<tr>
          <td colspan="2"><button type="submit">Save</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>

</body>
</html>
