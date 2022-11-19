<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="teacher.tutorial.form.label.type" path="type">
		<acme:input-option code="THEORY" value="THEORY" selected="${type == 'THEORY'}"/>
		<acme:input-option code="LAB" value="LAB" selected="${type == 'LAB'}"/>
	</acme:input-select>
	<acme:input-textbox code="teacher.tutorial.form.label.title" path="title"/>
	<acme:input-textbox code="teacher.tutorial.form.label.ticker" path="ticker"/>	
	<acme:input-textarea code="teacher.tutorial.form.label.abstract-text" path="abstractText"/>	
	<acme:input-money code="teacher.tutorial.form.label.cost" path="cost"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="teacher.tutorial.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	<acme:input-url code="teacher.tutorial.form.label.info" path="info"/>	
	<acme:input-textbox code="teacher.tutorial.form.label.teacher" path="teacher.userAccount.username"/>
	
	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:input-textbox code="teacher.tutorial.form.label.teacher"
				path="teacher.userAccount.username" readonly="true" />
			<acme:submit code="teacher.tutorial.form.button.delete"
				action="/teacher/tutorial/delete" />
			<acme:submit code="teacher.tutorial.form.button.update"
				action="/teacher/tutorial/update" />
			<acme:submit code="teacher.tutorial.form.button.publish"
				action="/teacher/tutorial/publish" />
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="teacher.tutorial.form.button.create"
				action="/teacher/tutorial/create" />
		</jstl:when>
	</jstl:choose>
</acme:form>