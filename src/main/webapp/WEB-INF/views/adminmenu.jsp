<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>RentTrack</h3>

<div class="tabs-group">
	<div class="tabs">
		<ul></ul>
		<div class="tab-content">
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}">Home</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}new">New Rental</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">			
				<h1><a href="${uri}owner">New Owner</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}search">Search</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}searchOwner">Search Owners</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}ownerExpireEmail/">Permit Expire Emails</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}reports/">Reports</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}settings/">Settings</a></h1>
			</div>
			<div class="tab-pane" style="display:none;">
				<h1><a href="${uri}logout">Logout</a></h1>
			</div>
		</div>
	</div>
</div>
