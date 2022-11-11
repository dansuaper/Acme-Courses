<%@page language="java"%> 
 
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%> 
 
<acme:list> 
	<acme:list-column code="teacher.help-request.list.label.ticker" path="ticker"/> 
	<acme:list-column code="teacher.help-request.list.label.startDate" path="startDate"/>	 
	<acme:list-column code="teacher.help-request.list.label.endDate" path="endDate"/>
	<acme:list-column code="teacher.help-request.list.label.status" path="status"/>
	<acme:list-column code="teacher.help-request.list.label.info" path="info"/>
</acme:list>                                                                                             