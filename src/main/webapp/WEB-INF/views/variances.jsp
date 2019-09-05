<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Variances</legend>
    <table>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Rental</th>
					<th scope="col">Date</th>				
					<th scope="col">Variance Text</th>
					<th scope="col">User</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rental.variances}" var="variance">
					<tr>				
						<td><a href="${uri}variance/${variance.id}">${variance.id}</a></td>
						<td>${rental.id}</td>					
						<td>${variance.dateFr}</td>
						<td>${variance.variance}</td>
						<td><c:if test="${not empty variance.user}">${variance.user}</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>

