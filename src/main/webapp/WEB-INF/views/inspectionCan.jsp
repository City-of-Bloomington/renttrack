<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
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
			<div class="form-group">
				<fieldset>			
					<div class="field-group">
						<label><a href="${uri}inspection/${inspectionCan.inspection.id}">Related Inspection ${inspectionCan.inspection.id}</a></label>
					</div>
					<div>
						<label>Component (Main title): </label>
						${inspectionCan.templateComponent.component}
					</div>
					<div class="field-group">
						<label>Secondary Title </label>
						<form:input path="title" size="50" />
					</div>					
					<div class="field-group">
						<label>First Paragraph</label>
						<form:textarea path="item1" rows="5" cols="70" />
					</div>
					<div class="field-group">
						<label>Item 2</label>
						<form:textarea path="item2" rows="5" cols="70" />
					</div>
					<div class="field-group">					
						<label>Item 3</label>
						<form:textarea path="item3" rows="5" cols="70" />
					</div>
				</fieldset>
				<fieldset>
					<details onclick="checkOpen(this)">
						<summary id="summary_id">Show more</summary>
						<div class="field-group">										
							<label>Item 4</label>
							<form:textarea path="item4" rows="5" cols="70" />
						</div>
						<div class="field-group">				
							<label>Item 5</label>
							<form:textarea path="item5" rows="3" cols="70" />
						</div>
						<div class="field-group">				
							<label>Item 6</label>
							<form:textarea path="item6" rows="3" cols="70" />
						</div>
						<div class="field-group">				
							<label>Item 7</label>
							<form:textarea path="item7" rows="3" cols="70" />
						</div>
						<div class="field-group">				
							<label>Item 8</label>
							<form:textarea path="item8" rows="3" cols="70" />
						</div>
					</details>
				</fieldset>
				<div>
					<div>
						<button type="submit">Update</button>
					</div>
				</div>
			</div>
		</fieldset>
  </form:form>
	<jsp:include page="footer.jsp" />
