<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>New Note</legend>
    <form:form action="${uri}noteSave" method="post" modelAttribute="note">
			<form:hidden path="rental.id" />			
      <table>
				<tr>
					<th>Related Rental</th>
					<td>${note.rental.id}</td>
				</tr>
				<tr>
					<th>Date</th>
					<td>${note.dateFr}</td>
				</tr>				
				<tr>
          <th>Note Text</th>
          <td>
            <form:textarea path="notes" cols="50" rows="5" /> 
            <form:errors path="notes" cssClass="error" />
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
