<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="header.jsp" />

<body>
	<script>
	function cleanText(obj, str){
		if(obj.value.indexOf(str) > -1){
		  obj.value="";
		}
	}
	</script>
  <fieldset>
    <legend>Owners/Agents Permits Expire Email</legend>
		<p>Please read before you start</p>
		<ul>
			<li>The system will gather the email addresses from the owners and agents in rental system and use them in the email list.</li>
			<li>Permits that have expiration date within the date range set below will only be selected</li>
			<li>It is recommended that you check the invalid emails first by clicking on 'Check Invalid Emails' button below.</li>
			<li>You may email a copy to yourself or/and to your manager by using the CC field. Make sure it is a complete email address such as 'hand@bloomington.in.gov'</li>
			<li>You can edit the email message as you see fit by editing the email paragraphs below.</li>
			<li>Click on the 'Send' button</li>
		</ul>
    <form:form action="${uri}expireEmailSend" method="post" enctype="multipart/form-data" modelAttribute="emailer" >
      <table class="vertaTable">
				<tr>
          <th>Expire Date From:</th>
          <td>
            <form:input path="dateFrom" size="10" cssClass="date" /> 
					</td>
				</tr>
				<tr>
          <th>Expire Date to:</th>
          <td>
            <form:input path="dateTo" size="10" cssClass="date" /> 
					</td>
				</tr>
				<tr>
          <th>Email From Address:</th>
          <td>
            <form:input path="emailFrom" size="40" /> 
					</td>
				</tr>				
				<tr>
          <th>CC Email:</th>
          <td>
            <form:input path="cc" size="40" onfocus="cleanText(this,'Enter an email address here (optional)');" /> 
					</td>
				</tr>
				<tr>
          <th>Email Subject:</th>
          <td>
            <form:input path="subject" size="40" /> 
					</td>
				</tr>
				<tr>
          <th>Email Introduction</th>
					<td></td>
				</tr>
				<tr>
          <td colspan="2">
            <textarea name="intro" rows="4" cols="80">Dear Owner/Manager/Agent

Our records indicate that the Rental Occupancy Permits for the rental property permit(s) listed below will expire on the listed dates:
						</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Unit Address(es) and expire dates will come next.
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Then the following paragraphs will follow. 
					</td>
				</tr>
				<tr>
          <td colspan="2">
            <textarea name="para1" rows="5" cols="80">Please contact this office at (812) 349-3420 to schedule an inspection to renew your permit. We cannot schedule inspections via email. Bloomington City Ordinance requires each rental unit to have a valid Rental Occupancy Permits. Please schedule your inspection in advance so all repairs and subsequent reinspections may occur prior to the expiration of your current permit.
						</textarea>
					</td>
				</tr>
				<tr>
          <td colspan="2">
            <textarea name="para2" rows="14" cols="80">If you have already contacted our office and scheduled your inspection, please disregard this email.

Thank You

Housing and Neighborhood Development
City Of Bloomington, Indiana

This is a courtesy reminder. The responsibility to schedule all inspections falls on property owners/managers.

***This is an automated generated email. Please do not reply to this email. If you have questions or concerns, please contact our department at the above mentioned phone number.
						</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						If you want to attach a file, you can attach it by clicking on Browse button (optional).
					</td>
				</tr>
				<tr>
					<th>Document File</th>
					<td>
						<input type="file" name="file" />
					</td>
				</tr>
				<tr>
					<th></th>
          <td>
						<button type="submit" name="action" value="Check">Check Invalid Emails</button>
						<button type="submit" name="action" value="Send">Send</button>
					</td>
        </tr>
      </table>
    </form:form>
  </fieldset>
	<br />
	<c:if test="${not empty emailLogs}">
		<jsp:include page="emailLogs.jsp" />		
	</c:if>
<jsp:include page="footer.jsp" />
