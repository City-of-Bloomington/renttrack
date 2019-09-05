<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty uri}">
	<c:set var="uri" value="/renttrack/" scope="application" />
	<c:set var="ADDRESS_CHECK_URL" value="https://bloomington.in.gov" scope="application" />
</c:if>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>renttrack</title>
		<link rel="stylesheet" href="${uri}css/system/system.css" type="text/css" media="all" />		
		<link rel="stylesheet" href="${uri}css/addressChooser.css" type="text/css" media="all" />
		<script type="text/javascript">
		  var APPLICATION_URL = "${uri}";
		</script>
	</head>
	<body>
		<header data-v-1738add8>
			<div>
				<a href="https://bloomington.in.gov" title="City of Bloomington, IN">
					<img src="./css/system/icons/city-of-bloomington-logo.svg" alt="City of Bloomington, IN" height="60" width="60" /> 
					<div>
						<h3>Bloomington.in.gov</h3> 
						<h4>John Hamilton, Mayor</h4>
					</div>
				</a> 
				<a href="${uri}" title="RentTrack">Rent Track</a> 
				<nav role="navigation" aria-labelledby="navigation" class="button-group">
					<a href="${uri}" title="Home" class="button">Home</a>
					<a href="${uri}new" title="New Rental" class="button">New Rental</a>
					<a href="${uri}owner" title="New Owner" class="button">New Owner</a>
					<a href="${uri}search" title="Search" class="button">Search</a>
					<a href="${uri}searchOwner" title="Search Owners" class="button">Search Owners</a>
					<a href="${uri}ownerExpireEmail" title="Permit Expire Emails" class="button">Permit Expire Emails</a>
					<a href="${uri}reports" title="Reports" class="button">Reports</a>
					<a href="${uri}settings" title="Settings" class="button">Settings</a>
					<a href="${uri}logout" title="Logout" class="button">Logout</a>
				</nav>
				<nav role="navigation dropdown" aria-label="navigation dropdown" class="navigation-dropdown">
					<details>
						<summary>Admin Menu</summary> 
						<ul class="right">
							<li>
								<a href="${uri}settings" title="Settings">Settings</a>
							</li>
							<li>
								<a href="${uri}users" title="Users">Users</a>
							</li>
						</ul>
					</details>
				</nav> 				
			</div>
		</header>
