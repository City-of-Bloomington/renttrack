<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<body>
  <fieldset>
    <legend>New Egress</legend>
    <form:form action="${uri}settings/egressSave" method="post" modelAttribute="egress">
			<details>
				<summary>Notes</summary>
				<ul>
					<li>Start year and end year are in 4 digit format, such as 1992</li>
					<li>If start year and end year are the same, you still need to enter both in start year and end year fields</li>
					<li>Height, width and sill height are integers in inches</li>
					<li>Area is double precision in square ft</li>
					<li>Do not use double quote for inches when entering width, height and sill height</li>
					<li>Do not use single quote for ft for area </li>
				</ul>
			</details>
      <table>
        <tr>
          <th>Start Year </th>
          <td><form:input path="startYear" size="4" maxlength="4" required="required" />  (yyyy) </td>
				</tr>
        <tr>
          <th>End Year </th>
          <td><form:input path="endYear" size="4" maxlength="4" required="required" />  (yyyy) </td>
				</tr>
        <tr>
          <th>Building Type </th>
          <td><form:radiobutton path="type" value="Single" /> Single
						<form:radiobutton path="type" value="Multi" /> Multi</td>
				</tr>
        <tr>
          <th>Width </th>
          <td><form:input path="width" size="2" maxlength="2" required="required" />  inches</td>
				</tr>
        <tr>
          <th>Height </th>
          <td><form:input path="height" size="2" maxlength="2" required="required" />  inches</td>
				</tr>
        <tr>
          <th>Sill Height </th>
          <td><form:input path="sillHeight" size="2" maxlength="2" required="required" />  inches</td>
				</tr>
        <tr>
          <th>Area 1st floor</th>
          <td><form:input path="area" size="8" maxlength="8" required="required" />  sq ft, all other <form:input path="area2" size="8" maxlength="8" /> sq ft</td>
				</tr>				
				<tr>
          <td><button type="submit">Save</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
</body>
</html>
