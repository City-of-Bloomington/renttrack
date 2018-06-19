<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="headerBasic.jsp" />		
<body>
	<fieldset>
		<legend>Pick Address</legend>
		<c:if test="${not empty message}">
			<h4>	${mesage} </h4>
		</c:if>
		<p>A number of addresses matched this address, please pick the right one from the list below</p>
		<form:form action="${uri}addressPopSave" method="post" modelAttribute="address">		
			<table width="60%" border="0">
				<tr>
					<td align="left" width="25%"><b>Select</b></td>			
					<td align="left"><b>Address</b></td>
				</tr>
				<c:if test="${not empty addresses}">
					<c:forEach items="${addresses}" var="addr">			
						<tr>
							<td align="left"><input type="radio" name="addrCombo" value="${addr.addrCombo}" /></td>
							<td align="left">${addr.streetAddress}</td>		
						</tr>
					</c:forEach>
					<tr>
						<td>&nbsp;</td>
						<td align="left">
							<button type="submit">Save</button>
						</td>
					</tr>
				</c:if>
			</table>
		</form:form>
	</fieldset>
	<jsp:include page="footer.jsp" />		
	

