<%@page language="java"%> 
 
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%> 
 
<acme:list> 
	<acme:list-column code="learner.followUp.list.label.sequenceNumber" path="sequenceNumber"/> 
	<acme:list-column code="learner.followUp.list.label.instantiationMoment" path="instantiationMoment"/>
	<acme:list-column code="learner.followUp.list.label.info" path="info"/>
</acme:list>                                                                                               