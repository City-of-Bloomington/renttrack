<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="../header.jsp" />
<body>
  <fieldset>
    <legend>New Standard Billing Fees</legend>
    <form:form action="${uri}settings/standardFeesSave" method="post" modelAttribute="standardFees">
			<ul>
				<li>Enter the rates for the following items in dollar amounts (XX.XX) format</li>
				<li>These rates will be used in next rental bill </li>
				<li>These rates will not affect the old issued bills</li>
			</ul>
      <table>
				<tr>
					<th>Single Unit Building Rate </th>
          <td>$<form:input path="singleUnitBuildingRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>Multi Unit Building Rate </th>
          <td>$<form:input path="multiUnitBuildingRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>Condo Unit Building Rate </th>
          <td>$<form:input path="condoUnitBuildingRate" size="10" required="required" /> 
          </td>
				</tr>						
				<tr>
					<th>Unit Rate </th>
          <td>$<form:input path="unitRate" size="10" required="required" /> 
          </td>
				</tr>				
				<tr>
					<th>Rooming House Rate </th>
          <td>$<form:input path="roomingBuildingRate" size="10" required="required" /> (rooming house type)
          </td>
				</tr>
				<tr>
					<th>Rooming Bath Rate </th>
          <td>$<form:input path="roomingBathRate" size="10" required="required" /> (for rooming housing type)
          </td>
				</tr>				
				<tr>
					<th>Reinspection Rate </th>
          <td>$<form:input path="reinspectionRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>No Showing Rate </th>
          <td>$<form:input path="noShowRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>Summary Rate </th>
          <td>$<form:input path="summaryRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>IDL Rate </th>
          <td>$<form:input path="idlRate" size="10" required="required" /> 
          </td>
				</tr>
				<tr>
					<th>Appeal Fee </th>
          <td>$<form:input path="appealFee" size="10" required="required" /> 
          </td>
				</tr>		
				<tr>
          <td><button type="submit">Save</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
</body>
</html>
