<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>New Owner</legend>
    <form:form action="${uri}ownerSaveWithLink" method="post" modelAttribute="owner">
			<input type="hidden" name="rentalId" value="${rentalId}" />
      <table class="vertaTable">
				<tr>
					<th>Related Rental</th>
					<td><a href="${uri}view/${rentalId}">${rentalId}</a>
					</td>
				</tr>
        <tr>
          <th>Name</th>
          <td>
            <form:input path="name" size="50" /> 
            <form:errors path="name" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Address</th>
          <td>
            <form:input path="address" size="50" /> 
            <form:errors path="address" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>City</th>
          <td>
            <form:input path="city" /> 
            <form:errors path="city" cssClass="error" />
					</td>
				</tr>
				<tr>
          <th>State</th>
          <td>				
						<form:input path="state" size="2" maxlength="2" /> 
            <form:errors path="state" cssClass="error" />
          </td>
				</tr>
				<tr>
					<th>Zip code</th>
					<td>
						<form:input path="zip" /> 
            <form:errors path="zip" cssClass="error" />						
          </td>
				</tr>				
				<tr>
          <th>Email</th>
          <td>
            <form:input path="email" size="65" /> 
            <form:errors path="email" cssClass="error" />
					</td>
				</tr>
				<tr>
          <th valign="top">Notes</th>
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
