<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<c:if test="${not empty message}">
	<h3>${message}</h3>
</c:if>
<fieldset>
  <legend>User Login</legend>
  <form:form action="${uri}loginVerify" method="post" modelAttribute="user2">
    <table>
        <tr>
          <th>Username</th>
          <td>
            <input type="text" name="username" size="10" maxlength="10" value="" /> 
          </td>
				</tr>
				<tr>
          <th>Password</th>
          <td>
            <input type="password" name="password" size="10" value="" />
          </td>
				</tr>
				<tr>
          <td><button type="submit">Login</button></td>
        </tr>
      </table>
    </form:form>
  </fieldset>
  <br />
</body>
</html>
