<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   Addresses
		<ul>
      <c:forEach items="${rental.addresses}" var="address">
				<li>	<a href="${uri}address/${address.id}"> ${address.streetAddress}	</a><c:if test="${address.isInvalidAddr()}">(Invalid)</c:if> </li>
      </c:forEach>
    </ul>

