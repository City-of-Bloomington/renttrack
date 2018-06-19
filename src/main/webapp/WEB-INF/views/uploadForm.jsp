<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
  <fieldset>
    <legend>File Uploads</legend>
		<form:form action="${uri}uploadFile" method="post"  enctype="multipart/form-data" modelAttribute="base">
			<form:hidden path="type" />
			<form:hidden path="id" />
			<table>
				<tr>
					<td>Type </td>
					<td>${base.type}</td>
				</tr>
				<tr>
					<td>ID </td>
					<td>${base.id}</td>
				</tr>
				<tr>
					<td>Upload File</td>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td>Notes</td>
					<td>
						<form:textarea path="notes" cols="50" rows="5" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">Submit</button>
					</td>
				</tr>
			</table>
		</form:form>
	</fieldset>

</body>
</html>
