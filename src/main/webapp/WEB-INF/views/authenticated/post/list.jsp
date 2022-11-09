<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.post.list.label.caption" path="caption"/>
	<acme:list-column code="authenticated.post.list.label.instantation-moment" path="instantationMoment"/>	
	<acme:list-column code="authenticated.post.list.label.message" path="message"/>
	<acme:list-column code="authenticated.post.list.label.info" path="info"/>	
	<acme:list-column code="authenticated.post.list.label.flag" path="flag"/>	
</acme:list>