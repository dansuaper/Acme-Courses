<%@page language="java"%> 
 
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%> 
 
<acme:list> 
	<acme:list-column code="learner.help-request.list.label.ticker" path="ticker"/> 
	<acme:list-column code="learner.help-request.list.label.budget" path="budget"/>
	<acme:list-column code="learner.help-request.list.label.start-date" path="startDate"/>	 
	<acme:list-column code="learner.help-request.list.label.end-date" path="endDate"/>
	<acme:list-column code="learner.help-request.list.label.status" path="status"/>
	 
</acme:list>   