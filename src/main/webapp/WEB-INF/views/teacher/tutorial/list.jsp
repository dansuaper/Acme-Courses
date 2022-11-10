<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="teacher.tutorial.list.label.type" path="type" width="20%"/>	
	<acme:list-column code="teacher.tutorial.list.label.title" path="title" width="20%"/>
	<acme:list-column code="teacher.tutorial.list.label.teacher" path="teacher.userAccount.username" width="20%"/>
	<acme:list-column code="teacher.tutorial.list.label.ticker" path="ticker" width="20%"/>	
	<acme:list-column code="teacher.tutorial.list.label.cost" path="cost" width="20%"/>
	
</acme:list>