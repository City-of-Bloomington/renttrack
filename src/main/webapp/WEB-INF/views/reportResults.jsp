<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<c:if test="${not empty message}">
  <h4> ${message} </h4>
</c:if>
  <fieldset>
    <legend>${reportTitle}</legend>
    <table class="wideTable">
      <tr>
				<c:forEach items="${resultTitles}" var="one">				
					<th>${one}</th>
			</c:forEach>
			</tr>
      <c:forEach items="${results}" var="line">
				<tr>
					<c:forEach items="${line}" var="one">					
						<td>${one}</td>
					</c:forEach>						
				</tr>
      </c:forEach>
    </table>
  </fieldset>
</body>
</html>
