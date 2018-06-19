<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
	<script>
	componentArr = Array();
	<c:forEach items="${components}" var="one" varStatus="loop">	
	componentArr[${loop.index}] = "${one}";
	</c:forEach>	
	function updateValue(item){
		var val = item.value;
		var jj = parseInt(val.substring(2));
		if(item.checked){
			document.getElementById(val).value=componentArr[jj];
		}
		else{
			document.getElementById(val).value="";			
		}
	}
	</script>
	
  <form:form action="${uri}templateSave" method="post" modelAttribute="template">
		<form:hidden path="rentalId" />
		<form:hidden path="buildingCnt" />
		<form:hidden path="buildingNum" />
		<form:hidden path="unitNum" />
		<form:hidden path="floorNum" />
		<c:if test="${not empty id}">
			<input type="hidden" name="id" value="${id}" />
		</c:if>
		<fieldset>
    <legend>New Inspection Template</legend>
		<h3>${unitInfo}</h3>
		<h4>${floorInfo}</h4>
		<p>Uncheck the components that are irrelevant and modify the component titles as needed.
    <table class="vertaTable">
			<tr>
				<th width="10%">Related Rental:</th>
				<td><a href="${uri}view/${rid}"> ${rid}</a></td>				
			</tr>
			<tr><th></th><td>Component Title</td></tr>
			<c:if test="${not empty components}">
				<c:forEach items="${components}" var="one" varStatus="loop">				
					<tr>
						<th>
							<input type="checkbox" name="components_2" value="t_${loop.index}" checked="checked" onchange="updateValue(this)" /></th><td>
							<input type="text" name="components" id="t_${loop.index}" value="${one}" size="30" />
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
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

