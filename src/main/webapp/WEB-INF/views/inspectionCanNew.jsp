<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
	<script>
		function checkOpen(elem){
			var open = elem.hasAttribute("open");
			if(open){
				document.getElementById("summary_id").innerHTML="Show more";
			}
			else{
				document.getElementById("summary_id").innerHTML="Show less";
			}
		}
	</script>
  <form:form action="${uri}inspectionCanSave" method="post" modelAttribute="inspectionCan">
		<form:hidden path="inspection.id" />
		<form:hidden path="templateComponent.id" />		
		<input type="hidden" name="type" id="can_type" value="" />	
		<fieldset>
    <legend>Add a can to inspection</legend>
			Please note the following:
			<ul>
				<li>Start typing the can title in the 'Can' field to pick from a list</li>
				<li>
					You may add a second title to your can by entering the title in 'Secondary Title'</li>
			</ul>
      <table class="vertaTable">
				<tr>
					<td><a href="${uri}inspection/${inspectionCan.inspection.id}">Related Inspection ${inspectionCan.inspection.id}</a></td>
				</tr>
				<tr>
          <td><b>Can </b></td>
				</tr>
				<tr>
          <td>
            <input type="text" name="can_title" value="" size="50" id="can_title" /> (can file reference)
          </td>
				</tr>
				<tr>
					<td><b>Component (Main title)</b></td>
				</tr>
				<tr>
					<td>${inspectionCan.templateComponent.component}</td>
				</tr>
        <tr>
          <td><b>Secondary Title </b></td>
				</tr>
				<tr>
          <td>
            <input type="text" name="title" size="50" /> 
          </td>
				</tr>				
        <tr>
          <td><b>First Paragraph</b></td>
				</tr>
				<tr>
          <td>
            <textarea name="item1" id="item1" rows="5" cols="70"></textarea> 
          </td>
				</tr>
        <tr>
          <td><b>Item 2</b></td>
				</tr>
				<tr>
					<td>
            <textarea name="item2" id="item2" rows="5" cols="70"></textarea>
					</td>
				</tr>
        <tr>
          <td><b>Item 3</b></td>
				</tr>
				<tr>
          <td>
            <textarea name="item3" id="item3" rows="5" cols="70"></textarea> 
          </td>
				</tr>
			</table>
			<details onclick="checkOpen(this)">
				<summary id="summary_id">Show More</summary>
				<table class="vertaTable">							
					<tr>
						<td><b>Item 4</b></td>
					</tr>
					<tr>
						<td>
							<textarea name="item4" id="item4" rows="3" cols="70"></textarea> 
          </td>
					</tr>
					<tr>
						<td><b>Item 5</b></td>
					</tr>
					<tr>
						<td>
							<textarea name="item5" id="item5" rows="3" cols="70"></textarea> 
						</td>
					</tr>
					<tr>
						<td><b>Item 6</b></td>
					</tr>
					<tr>
						<td>
							<textarea name="item6" id="item6" rows="3" cols="70"></textarea> 
						</td>
					</tr>
					<tr>
						<td><b>Item 7</b></td>
					</tr>
					<tr>
						<td>
							<textarea name="item7" id="item7" rows="3" cols="70"></textarea> 
						</td>
					</tr>
					<tr>
						<td><b>Item 8</b></td>
					</tr>
					<tr>
						<td>
							<textarea name="item8" id="item8" rows="3" cols="70"></textarea> 
						</td>
					</tr>
				</table>
			</details>
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
