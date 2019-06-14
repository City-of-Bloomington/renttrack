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
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>		
  <form:form action="${uri}inspectionCanUpdate" method="post" modelAttribute="inspectionCan">
		<form:hidden path="id" />
		<form:hidden path="inspection.id" />
		<form:hidden path="templateComponent.id" />		
		<form:hidden path="type" />
		<fieldset>
			<legend>Edit Inspection Can </legend>
			<table class="vertaTable">
				<tr>
					<td><a href="${uri}inspection/${inspectionCan.inspection.id}">Related Inspection ${inspectionCan.inspection.id}</a></td>
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
						<form:input path="title" size="50" /> 
					</td>
				</tr>				
				<tr>
					<td><b>First Paragraph</b></td>
				</tr>
				<tr>
					<td>
						<form:textarea path="item1" rows="5" cols="70" />
					</td>
				</tr>
				<tr>
					<td><b>Item 2</b></td>
				</tr>
				<tr>
					<td>
						<form:textarea path="item2" rows="5" cols="70" />
					</td>
				</tr>
				<tr>
					<td><b>Item 3</b></td>
				</tr>
				<tr>
					<td>
						<form:textarea path="item3" rows="5" cols="70" />
					</td>
				</tr>
			</table>
			<details onclick="checkOpen(this)">
				<summary id="summary_id">Show more</summary>
				<table class="vertaTable">
				<tr>
					<td><b>Item 4</b></td>
				</tr>
				<tr>
					<td>
						<form:textarea path="item4" rows="5" cols="70" />
					</td>
				</tr>
					<tr>
						<td><b>Item 5</b></td>
					</tr>
					<tr>
						<td>
							<form:textarea path="item5" rows="3" cols="70" />
						</td>
					</tr>
					<tr>
						<td><b>Item 6</b></td>
					</tr>
					<tr>
						<td>
							<form:textarea path="item6" rows="3" cols="70" />
						</td>
					</tr>
					<tr>
						<td><b>Item 7</b></td>
					</tr>
					<tr>
						<td>
							<form:textarea path="item7" rows="3" cols="70" />
						</td>
					</tr>
					<tr>
						<td><b>Item 8</b></td>
					</tr>
					<tr>
						<td>
						<form:textarea path="item8" rows="3" cols="70" />
						</td>
					</tr>
				</table>
			</details>
		</fieldset>
		<fieldset>
		<table class="submitTable">
			<tr>
        <td><button type="submit">Update</button></td>
      </tr>
    </table>
  </fieldset>		
  </form:form>
	<jsp:include page="footer.jsp" />
