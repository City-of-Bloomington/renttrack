<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>Update Room</legend>
		<details>
			<summary>Instructions</summary>
			<ul>
				<li>Rooms are kitchen, living room and bedrooms</li>
				<li>Do not include hallway, closets, bathrooms</li>
				<li>Measures are in feet-inch format for two dimensions such as
					a room that is 7-8 X 10-4 is 7 ft and 8 inches width and 10 ft and 4 inches length</li>
				<li>Room type and measures will appear in inspection reports</li>
			</ul>
		</details>
    <form:form action="${uri}roomUpdate" method="post" modelAttribute="room">
			<form:hidden path="rentalUnit.id" />
			<form:hidden path="id" />			
      <table>
				<tr>
					<th>Related Unit</th>
					<td>
						<a href="${uri}unit/${room.rentalUnit.id}">${room.rentalUnit.id}</a>
					</td>
				</tr>
        <tr>
          <th>Identifier</th>
          <td>
            <form:input path="identifier" /> 
            <form:errors path="identifier" cssClass="error" />
          </td>
				</tr>
				<tr>
          <th>Type</th>
          <td>
            <form:select path="type">
							<form:option value="0" label="Pick Type" />
							<form:options items="${types}" />
						</form:select>
          </td>
				</tr>
				<tr><td colspan="2">Measures are in xx-xx,xx-xx format,
					such as (10-2 X 7-8)</td>
				</tr>
					<tr>
          <th>Measures</th>
          <td>
            <form:input path="measures" size="30" /> ft-in,ft-in
          </td>
				</tr>				
				<tr>
          <td colspan="2"><button type="submit">Save Changes</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>

</body>
</html>
