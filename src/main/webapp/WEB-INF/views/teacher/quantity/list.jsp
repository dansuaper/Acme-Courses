<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="teacher.quantity.list.label.type" path="tutorial.type"/>
	<acme:list-column code="teacher.quantity.list.label.ticker" path="tutorial.ticker"/>
	<acme:list-column code="teacher.quantity.list.label.cost" path="tutorial.cost"/>
	<acme:list-column code="teacher.quantity.list.label.amount" path="amount"/>
	<acme:list-column code="teacher.quantity.list.label.time-unit" path="timeUnit"/>
</acme:list>

<acme:button code="teacher.quantity.list.button.create" action="/teacher/quantity/create?id=${courseId}"/>