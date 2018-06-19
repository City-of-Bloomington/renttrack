<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Variances</legend>
    <table class="wideTable">
      <tr>
				<th>ID</th>
				<th>Rental</th>
				<th>Date</th>				
				<th>Variance Text</th>
				<th>User</th>
			</tr>
      <c:forEach items="${rental.variances}" var="variance">
				<tr>				
					<td><a href="${uri}variance/${variance.id}">${variance.id}</a></td>
					<td>${rental.id}</td>					
					<td>${variance.dateFr}</td>
					<td>${variance.variance}</td>
					<td><c:if test="${not empty variance.user}">${variance.user}</c:if></td>
				</tr>
      </c:forEach>
    </table>
  </fieldset>

