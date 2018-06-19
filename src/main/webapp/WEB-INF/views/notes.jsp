<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <fieldset>
    <legend>Notes</legend>
    <table class="wideTable">
      <tr>
				<th>Date</th>				
				<th>Note Text</th>
				<th>User</th>
			</tr>
      <c:forEach items="${rental.rentalNotes}" var="note">
				<tr>				
					<td>${note.dateFr}</td>
					<td>${note.notes}</td>
					<td>${note.user}</td>
				</tr>
      </c:forEach>
    </table>
  </fieldset>

