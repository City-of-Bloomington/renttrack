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
  <form:form action="${uri}inspectionCanSave" method="post" modelAttribute="inspectionCan">
		<form:hidden path="inspection.id" />
		<form:hidden path="templateComponent.id" />		
		<input type="hidden" name="type" id="can_type" value="" />	
		<fieldset>
			<legend>Add a can to inspection</legend>
			<div class="form-group">		
				Please note the following:
				<ul>
					<li>Start typing the can title in the 'Can' field to pick from a list</li>
					<li>
						You may add a second title to your can by entering the title in 'Secondary Title'</li>
				</ul>
				<div>
					<label><a href="${uri}inspection/${inspectionCan.inspection.id}">Related Inspection ${inspectionCan.inspection.id}</a></label>
				</div>
				<div class="field-group">		
					<label>Can </label>
					<input type="text" name="can_title" value="" size="50" id="can_title" /> (can file reference)
				</div>
				<div>		
					<label>Component (Main title)</label>
					{inspectionCan.templateComponent.component}
				</div>
				<div class="field-group">		
					<label>Secondary Title </label>
					<input type="text" name="title" size="50" /> 
				</div>
				<div class="field-group">					
					<label>First Paragraph</label>
					<textarea name="item1" id="item1" rows="5" cols="70"></textarea> 
				</di>
				<div class="field-group">		
					<label>Item 2</label>
					<textarea name="item2" id="item2" rows="5" cols="70"></textarea>
				</div>
				<div class="field-group">		
					<label>Item 3</label>
					<textarea name="item3" id="item3" rows="5" cols="70"></textarea>
				</div>
				<details onclick="checkOpen(this)">
					<summary id="summary_id">Show More</summary>
				</div>
				<div class="field-group">	
					<label>Item 4</label>
					<textarea name="item4" id="item4" rows="3" cols="70"></textarea>
				</div>
				<div class="field-group">	
					<label>Item 5</label>
					<textarea name="item5" id="item5" rows="3" cols="70"></textarea> 
				</div>
				<div class="field-group">	
					<label>Item 6</label>
					<textarea name="item6" id="item6" rows="3" cols="70"></textarea> 
				</div>
				<div class="field-group">	
					<label>Item 7</label>
					<textarea name="item7" id="item7" rows="3" cols="70"></textarea> 
				</div>
				<div class="field-group">	
					<label>Item 8</label>
					<textarea name="item8" id="item8" rows="3" cols="70"></textarea> 
				</div>
				</details>
				<div>
					<div>
						<button type="submit">Save</button>
					</div>
				</div>
			</div>
		</fieldset>
  </form:form>
	<jsp:include page="footer.jsp" />
