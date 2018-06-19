<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<table class="wideTable">
	<caption>Unit Rooms</caption>
  <tr>
		<th>ID</th>
		<th>Identifier</th>
		<th>Type</th>
		<th>Measures</th>
		<th>Action</th>
	</tr>
  <c:forEach items="${unit.unitRooms}" var="room" varStatus="row">
		<tr>
			<td>
			<a href="${uri}roomEdit/${room.id}">Edit Room ${room.id}</a>
			</td>
			<td>${room.identifier}</td>
			<td>${room.type}</td>
			<td>${room.measures}</td>			
			<td>
				<a href="${uri}roomDelete/${room.id}">Remove</a> <br />
			</td>
		</tr>
  </c:forEach>
</table>

