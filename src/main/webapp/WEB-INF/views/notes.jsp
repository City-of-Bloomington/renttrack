<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Notes</legend>
    <table>
			<thead>
				<tr>
					<th scope="col">Date</th>				
					<th scope="col">Note Text</th>
					<th scope="col">User</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rental.rentalNotes}" var="note">
					<tr>				
						<td>${note.dateFr}</td>
						<td>${note.notes}</td>
						<td>${note.user}</td>
					</tr>
				</c:forEach>
			</tbody>
    </table>
  </fieldset>

