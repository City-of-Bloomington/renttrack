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
<c:if test="${not empty message}">
	<p>${message}</p>
</c:if>
<fieldset>
	<legend>Add a New Component</legend>
	<div class="form-group">
		<fieldset>	
			<details>
				<summary>Instructions</summary>
				<ul>
					<c:if test="${unitCnt > 1}">
						<li>Pick the unit that this component belongs to </li>
						<li>The new component will be added to the end list of components of that unit</li>
					</c:if>
					<c:if test="${floorCnt > 1}">
						<li>Pick the floor that this component belongs to </li>
						<li>The new component will be added to the end list of components of that floor</li>
					</c:if>		
					<li>Enter a component name </li>
					<li>Hit the 'Save' button</li>
				</ul>
			</details>
			<form:form action="${uri}componentSave" method="post" modelAttribute="component">
				<form:hidden path="inspectionTemplate.id" />
				<form:hidden path="buildingNum" />
				
				<c:if test="${unitCnt == 1}">
					<input type="hidden" name="unitNum" value="0" />
				</c:if>
				<c:if test="${floorCnt == 1}">
					<input type="hidden" name="floorNum" value="0" />
				</c:if>			
					<div class="field-group">		
						<label>Component Title</label>
						<form:input path="component" size="40" />
					</div>
				<c:if test="${unitCnt > 1}">
					<div class="field-group">		
						<label>Unit:</label>
						<select name="unitNum">
							<c:forEach begin="0" end="${unitCnt-1}" varStatus="loop">
								<option value="${loop.index}">${loop.count}</option>
							</c:forEach>							
						</select>
					</div>
				</c:if>
				<c:if test="${floorCnt > 1}">
					<div class="field-group">		
						<label>Floor:</label>
							<select name="floorNum">
								<c:forEach begin="0" end="${floorCnt-1}" varStatus="loop">
									<option value="${loop.index}">${loop.count}</option>
								</c:forEach>									
							</select>
					</div>
				</c:if>				
				<div>
					<div>
						<button type="submit">Save</button>
					</div>
				</div>
		</fieldset>
	</div>
	</form:form>
	<br />
	<a href="#" onclick="javascript:window.close()">Close This Window</a>
<jsp:include page="footer.jsp" />

