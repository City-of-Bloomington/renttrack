<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>RentTrack</h3>
<div class="menu">
	<ul class="menu">
		<li class="menu">
			<a href="${uri}">Home</a>
		</li>
		<li class="menu">
			<a href="${uri}new">New Rental</a>
		</li>
		<li class="menu">
			<a href="${uri}owner">New Owner</a>
		</li>				
		<li class="menu">
			<a href="${uri}search">Search</a>
		</li>
		<li class="menu">
			<a href="${uri}searchOwner">Search Owners</a>
		</li>
		<li class="menu">
			<a href="${uri}ownerExpireEmail/">Permit Expire Emails</a>
		</li>								
		<li class="menu">
			<a href="${uri}reports/">Reports</a>
		</li>				
		<li class="menu">
			<a href="${uri}logout">Logout</a>
		</li>				
	</ul>
</div>
<br />
