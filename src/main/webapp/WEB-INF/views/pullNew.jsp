<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}pullSave" method="post" modelAttribute="pull">
		<form:hidden path="rental.id" />			
  <fieldset>
    <legend>New Pull History</legend>
      <table>
				<tr>
          <th>Pull Date</th>
          <td>
            <form:input path="dateFr" /> 
            <form:errors path="dateFr" cssClass="error" />
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
          <th>Completed?</th>
          <td>
            <form:checkbox path="completed" value="y" />
          </td>
				</tr>
			</table>
	</fieldset>
	<fieldset>
		<table class="submitTable">
			<tr>
        <td><button type="submit">Save</button></td>
      </tr>
    </table>
  </fieldset>		
  </form:form>
	<jsp:include page="footer.jsp" />

