<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="teacher.help-request.form.label.ticker" path="ticker"/>
	<acme:input-moment code="teacher.help-request.form.label.startDate" path="startDate"/>
	<acme:input-moment code="teacher.help-request.form.label.endDate" path="endDate"/>
	<acme:input-textbox code="teacher.help-request.form.label.info" path="info"/>
	<acme:input-textbox code="teacher.help-request.form.label.status" path="status"/>
	<acme:input-textbox code="teacher.help-request.form.label.budget" path="budget"/>
	<acme:input-textbox code="teacher.help-request.form.label.username" path="teacher.userAccount.username"/>
	<acme:input-textbox code="teacher.help-request.form.label.school" path="teacher.school"/>
	<acme:input-textbox code="teacher.help-request.form.label.teacher-link" path="teacher.info"/>
	<acme:input-textbox code="teacher.help-request.form.label.statement" path="teacher.statement"/>
	
	<acme:button code="teacher.help-request.form.button.follow-up" action="/teacher/follow-up/listFollowUp?id=${id}"/>
</acme:form>