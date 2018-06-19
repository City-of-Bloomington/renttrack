<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />
<script>
window.onunload = refreshParent;
function refreshParent() {
	window.opener.location.reload();
	window.close();
}
</script>
<body>
	<p>If you make any change, click on the 'Save Changes' button </p> 
	<form:form action="${uri}componentUpdate" method="post" modelAttribute="component">
		<form:hidden path="id" />
		<form:hidden path="inspectionTemplate.id" />
		<form:hidden path="buildingNum" />		
		<form:hidden path="unitNum" />
		<form:hidden path="floorNum" />
		<fieldset>
			<legend>Update Component</legend>
			<table>
				<tr>
					<th>Component Title</th>
				</tr>
				<tr>
					<td>
						<form:input path="component" size="40" />
					</td>
				</tr>
				<tr>
					<th>
							<button type="submit">Save Changes</button>
					</th>
				</tr>				
			</table>
		</fieldset>		
	</form:form>
	<br />
	<a href="#" onclick="javascript:window.close()">Close This Window</a>
<jsp:include page="footer.jsp" />

