<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<body>
  <fieldset>
    <legend>Edit Inspection Can</legend>
    <form:form action="${uri}settings/canUpdate" method="post" modelAttribute="can">
			<form:hidden path="id" />
			Please note the following:
			<ul>
				<li>If this can has multiple paragraphs add each one in a separate box, first paragraph in 'First Paragraph', second in item 2, ..</li>
				<li>if the can has list or bullets, add each bullet in a separate box.</li>
				<li>If the list starts with a paragraph add that to 'First Paragraph' and add the list items each in separate box field starting with 'Item 2'.</li>
				<li>If the list does not have a paragraph, skip the first paragraph and start inserting list items starting with 'Item 2' box.</li>
			</ul>
      <table>
        <tr>
          <th>Can Title</th>
          <td>
            <form:input path="title" size="70" required="required" />(file ref) 
            <form:errors path="title" cssClass="error" />
          </td>
				</tr>
				<tr>
					<td colspan="2">For 'Text Type' below If the 'Can' consists of one or more paragraphs then click on 'Paragraph' else click on 'List'.
					</td>
        <tr>
          <th>Text Type </th>
          <td>
            <form:radiobutton path="type" value="Paragraph" /> Paragraph
						<form:radiobutton path="type" value="List" /> List (bullets)
            <form:errors path="type" cssClass="error" />
          </td>
				</tr>
				<tr>
				</td>
        <tr>
          <th>First Paragraph</th>
          <td>
            <form:textarea path="item1" rows="5" cols="70" /> 
            <form:errors path="item1" cssClass="error" />
          </td>
				</tr>
        <tr>
          <th>Item 2</th>
          <td>
            <form:textarea path="item2" rows="5" cols="70" /> 
            <form:errors path="item2" cssClass="error" />
          </td>
				</tr>
        <tr>
          <th>Item 3</th>
          <td>
            <form:textarea path="item3" rows="5" cols="70" /> 
            <form:errors path="item3" cssClass="error" />
          </td>
				</tr>
        <tr>
          <th>Item 4</th>
          <td>
            <form:textarea path="item4" rows="5" cols="70" /> 
            <form:errors path="item4" cssClass="error" />
          </td>
				</tr>
       <tr>
          <th>Item 5</th>
          <td>
            <form:textarea path="item5" rows="2" cols="70" /> 
            <form:errors path="item5" cssClass="error" />
          </td>
				</tr>
       <tr>
          <th>Item 6</th>
          <td>
            <form:textarea path="item6" rows="2" cols="70" /> 
            <form:errors path="item6" cssClass="error" />
          </td>
				</tr>
       <tr>
          <th>Item 7</th>
          <td>
            <form:textarea path="item7" rows="2" cols="70" /> 
            <form:errors path="item7" cssClass="error" />
          </td>
				</tr>
       <tr>
          <th>Item 8</th>
          <td>
            <form:textarea path="item8" rows="2" cols="70" /> 
            <form:errors path="item8" cssClass="error" />
          </td>
				</tr>				
				<tr>
          <td><button type="submit">Update</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
</body>
</html>
