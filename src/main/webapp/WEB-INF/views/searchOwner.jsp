<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}ownersFind" method="post" modelAttribute="owner">	
  <fieldset>
    <legend>Search Owners</legend>
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>
    <table class="vertaTable">
			<tr><th>&nbsp;</th>
				<td>Name</td>
			</tr>
      <tr>
        <th>Owner </th>
        <td>
					<form:input path="name" size="30" id="owner_name" />ID:
          <form:input path="ownerId" size="10" id="owner_id" /> 
        </td>
			</tr>
			<tr>
				<th>Address</th>
				<td>
          <form:input path="address" size="30" /> 
				</td>
			</tr>
      <tr>
        <th>City</th>
        <td>
					<form:input path="city" size="30" /> State:
					<form:input path="state" size="2" /> Zip:
          <form:input path="zip" size="10" /> 
        </td>
			</tr>
			<tr>
				<th>Email</th>
				<td>
          <form:input path="email" size="30" /> 
				</td>
			</tr>
			<tr>
				<th>Phone</th>
				<td>
          <form:input path="phone" size="10" /> 
				</td>
			</tr>
			<tr>
				<th>Without Email</th>
				<td>
          <form:checkbox path="noEmail" value="y"  /> Owners with no email
				</td>
			</tr>
    </table>
  </fieldset>
	<fieldset>
	<table class="submitTable">
		<tr>
			<td>&nbsp;</td>
      <td><button type="submit">Submit</button></td>
    </tr>
	</table>
	</fieldset>	
  </form:form>
	<jsp:include page="footer.jsp" />

