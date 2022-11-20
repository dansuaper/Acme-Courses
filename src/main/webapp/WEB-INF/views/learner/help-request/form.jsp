<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="learner.help-request.form.label.ticker" path="ticker"/>
	<acme:input-moment code="learner.help-request.form.label.start-date" path="startDate"/>
	<acme:input-moment code="learner.help-request.form.label.end-date" path="endDate"/>
	<acme:input-textbox code="learner.help-request.form.label.statement" path="statement"/>
	<acme:input-money code="learner.help-request.form.label.budget" path="budget"/>
	<jstl:if test="${command != 'create'}">
		<jstl:if test="${differentCurrency == true}">
			<acme:input-money code="learner.help-request.form.label.convertir" path="convertir" readonly="true"/>
		</jstl:if>
	<acme:input-textbox code="learner.help-request.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="learner.help-request.form.label.info" path="info"/>
	<jstl:if test="${published == true}">
	<acme:input-textbox code="learner.help-request.form.label.teacher-username" path="teacher.userAccount.username"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-school" path="teacher.school"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-info" path="teacher.info"/>
	<acme:input-textbox code="learner.help-request.form.label.teacher-statement" path="teacher.statement"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			 <acme:input-select code="learner.help-request.form.label.teacher" path="teacherId">
	   			<jstl:forEach items="${teachers}" var="teacher">
					<acme:input-option code="${teacher.getUserAccount().getUsername()}" value="${teacher.getId()}" />
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}">
			<acme:submit code="learner.help-request.form.button.update" action="/learner/help-request/update"/> 
			<acme:submit code="learner.help-request.form.button.delete" action="/learner/help-request/delete"/>
			<acme:submit code="learner.help-request.form.button.publish" action="/learner/help-request/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="learner.help-request.form.button.create" action="/learner/help-request/create"/>
		</jstl:when>
	</jstl:choose>	
	<acme:button code="learner.help-request.form.button.follow-up" action="/learner/follow-up/listFollowUp?id=${id}"/>
	
</acme:form>