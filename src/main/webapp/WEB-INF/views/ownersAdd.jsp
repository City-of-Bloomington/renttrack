<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<jsp:include page="header.jsp" />
<ul>
	<li>You can existing owners to this rental by typing his/her name in the name field and then pick from the list</li>
	<li>You can add one owner at a time</li>
	<li>If this is a new owner, click on 'New Owner'.</li>
</ul>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>		
  <fieldset>
    <legend>Add Existing Owners To Rental</legend>
    <form:form action="${uri}rentalOwnerSave" method="post" modelAttribute="rentalOwner">
			<form:hidden path="rental.id" />
      <table class="verticalTable">
				<tr>
					<th>Rental ID</th>
					<td><a href="${uri}view/${rentalOwner.rental.id}">${rentalOwner.rental.id}</a></td>
				</tr>
				<tr><td colspan="2">To add an existing owner start type the name and then pick from a list, you can add one owner at a time.</td>
				</tr>
        <tr>
          <th>Owner Name</th>
          <td>
            <input type="text" name="ownerName" value="" size="30" id="owner_name" /> Owner ID:
						<input type="text" name="owner.id" value="" size="10" id="owner_id"  required="required" />
          </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
          <td colspan="2">
						<button type="submit">Save </button>
						<a href="${uri}ownerWithLink/${rentalOwner.rental.id}">New Owner</a>
					</td>
      </table>
    </form:form>
		<c:if test="${rental.hasOwners()}">
			<jsp:include page="rentalOwners.jsp" />	
		</c:if>
  </fieldset>
  <br />
	<jsp:include page="footer.jsp" />		
