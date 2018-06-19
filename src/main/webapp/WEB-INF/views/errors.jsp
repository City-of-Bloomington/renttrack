<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />	
  <fieldset>
    <legend>Error</legend>
		The following error(s) happened <br />		
		<c:if test="${not empty message}">
			<p>
			${message}
			</p>
		</c:if>
	</fieldset>
</body>
</html>
