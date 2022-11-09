<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="learner.help-request.form.label.ticker" path="ticker"/>
	<acme:input-moment code="learner.help-request.form.label.start-date" path="startDate"/>
	<acme:input-moment code="learner.help-request.form.label.end-date" path="endDate"/>
	<acme:input-textbox code="learner.help-request.form.label.statement" path="statement"/>
	<acme:input-money code="learner.help-request.form.label.budget" path="budget"/>
	<acme:input-textbox code="learner.help-request.form.label.info" path="info"/>
	<acme:input-textbox code="learner.help-request.form.label.status" path="status"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-username" path="teacher.userAccount.username"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-school" path="teacher.school"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-info" path="teacher.info"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-statement" path="teacher.statement"/>

</acme:form>