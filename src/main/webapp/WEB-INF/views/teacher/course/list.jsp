<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="teacher.course.list.label.ticker" path="ticker"/>
	<acme:list-column code="teacher.course.list.label.caption" path="caption"/>
	<acme:list-column code="teacher.course.list.label.link" path="link"/>
	<acme:list-column code="teacher.course.list.label.teacher" path="teacher.userAccount.username"/>
</acme:list>