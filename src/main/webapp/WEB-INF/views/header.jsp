<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:if test="${empty uri}">
	<c:set var="uri" value="/renttrack/" scope="application" />
</c:if>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>renttrack</title>
		<link rel="stylesheet" href="${uri}js/jquery-ui-1.12.1.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${uri}js/jquery.ui.theme-1.12.1.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${uri}css/rentals.css" type="text/css" media="all" />		
		<script type="text/javascript">
			var APPLICATION_URL = "${uri}";
		</script>
	</head>
	<body>
		<jsp:include page="adminmenu.jsp" />
