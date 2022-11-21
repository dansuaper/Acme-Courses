<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="teacher.help-request.form.label.ticker" path="ticker" readonly="true"/>
	<acme:input-moment code="teacher.help-request.form.label.start-date" path="startDate" readonly="true"/>
	<acme:input-moment code="teacher.help-request.form.label.end-date" path="endDate" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.statement" path="statement" readonly="true"/>
	<acme:input-money code="teacher.help-request.form.label.budget" path="budget" readonly="true"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="teacher.help-request.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="teacher.help-request.form.label.info" path="info" readonly="true"/>
	
	<jstl:if test="${status!='PROPOSED'}">
		<acme:input-textbox code="teacher.help-request.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'PROPOSED'}">
		<acme:input-select path="status" code="teacher.help-request.form.label.status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>		
	</jstl:if>
	
	<acme:input-textbox code="teacher.help-request.form.label.username" path="learner.userAccount.username" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.school" path="learner.school" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.teacher-link" path="learner.info" readonly="true"/>
	<acme:input-textbox code="teacher.help-request.form.label.statement" path="learner.statement" readonly="true"/>
	<jstl:if test="${status == 'ACCEPTED'}">	
	<acme:button code="master.menu.learner.follow-ups-create" action="/teacher/follow-up/create?helpRequestId=${helpRequestId}"/>
	</jstl:if>
	
	<acme:submit test="${command == 'show' && status == 'PROPOSED'}" code="teacher.help-request.form.button.update" action="/teacher/help-request/update"/>
</acme:form>