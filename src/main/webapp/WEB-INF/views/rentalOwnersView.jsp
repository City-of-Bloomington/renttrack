<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Owner(s)</legend>
		<ul>
			<c:forEach items="${rental.rentalOwners}" var="one">
				<li>				
					<a href="${uri}owner/${one.owner.id}">${one.owner.name}</a> (${one.owner.address} - ${one.owner.cityStateZip}) (${one.owner.phonesInfo})</li>
			</c:forEach>
		</ul>
</fieldset>
