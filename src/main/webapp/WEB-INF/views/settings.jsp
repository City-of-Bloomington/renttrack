<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />
<body>
  <fieldset>
    <legend>Settings</legend>
		<ul>
			<li><a href="${uri}users/">Users</a></li>
			<li><a href="${uri}settings/buildingTypes">Building Types</a></li>
			<li><a href="${uri}settings/inspectionTypes">Inspection Types</a></li>
			<li><a href="${uri}settings/cans">Inspection Cans</a></li>			
			<li><a href="${uri}settings/propertyTypes">Property Types</a></li>
			<li><a href="${uri}settings/pullReasons">Pull Reasons</a></li>			
			<li><a href="${uri}settings/rentalStatuses">Rental Statuses</a></li>
			<li><a href="${uri}settings/zonings">Zonings</a></li>
			<li><a href="${uri}login">Login</a></li>			
			<li>Foundation Types
				<ul>
					<li>Slab</li>
					<li>Crawl Space</li>
					<li>Basement</li>
					<li>Celat</li>
					<li>Other</li>
				</ul>
			</li>
			<li>Heat Source Types
				<ul>
					<li>Gas</li>
					<li>Electric</li>
					<li>Other</li>
				 </ul>
			</li>
			<li>Time Status Types (inspections)
				<ul>
					<li>In Progress</li>
					<li>Completed</li>
				</ul>
			</li>
		</ul>
  </fieldset>
</body>
</html>
