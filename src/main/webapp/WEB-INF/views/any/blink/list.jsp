<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="any.blink.list.label.instantation-moment" path="instantationMoment" width="20%"/>
	<acme:list-column code="any.blink.list.label.caption" path="caption" width="20%"/>	
	<acme:list-column code="any.blink.list.label.author-alias" path="authorAlias" width="20%"/>
	<acme:list-column code="any.blink.list.label.message" path="message" width="20%"/>	
	<acme:list-column code="any.blink.list.label.email" path="email" width="20%"/>	
</acme:list>

<acme:button code="any.blink.list.button.create" action="/any/blink/create"/>