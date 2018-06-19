<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <form:form action="${uri}varianceSave" method="post" modelAttribute="variance">
		<form:hidden path="rental.id" />
		<fieldset>
			<legend>New Variance</legend>
      <table class="vertaTable">
				<tr>
					<th>Related Rental</th>
					<td>${variance.rental.id}</td>
				</tr>				
				<tr>
          <th>Date</th>
          <td>
            <form:input path="dateFr" class="date" /> 
            <form:errors path="date" cssClass="error" />
					</td>
				</tr>
				<tr>
          <th>Variance Text</th>
          <td>
            <form:textarea path="variance" cols="50" rows="5" /> 
            <form:errors path="variance" cssClass="error" />
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
	<br />
	<jsp:include page="footer.jsp" />
