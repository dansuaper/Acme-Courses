<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="teacher.course.form.label.ticker" path="ticker"/>	
	<acme:input-textbox code="teacher.course.form.label.caption" path="caption"/>	
	<acme:input-textarea code="teacher.course.form.label.abstract-text" path="abstractText"/>
	<acme:input-textbox code="teacher.course.form.label.link" path="link"/>		
	
	<jstl:choose>
		<jstl:when test="${command == 'show' && published == true}">
			<acme:input-textbox code="teacher.course.form.label.teacher" path="teacher.userAccount.username" readonly="true"/>
			<acme:input-textbox code="teacher.course.form.label.cost" path="cost"/>
			<acme:input-textbox code="teacher.course.form.label.published" path="published" readonly="true"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:input-textbox code="teacher.course.form.label.teacher" path="teacher.userAccount.username" readonly="true"/>
			<acme:input-textbox code="teacher.course.form.label.cost" path="cost" readonly="true"/>
			<acme:submit code="teacher.course.form.button.delete" action="/teacher/course/delete"/>
			<acme:submit code="teacher.course.form.button.update" action="/teacher/course/update"/>
			<acme:submit code="teacher.course.form.button.publish" action="/teacher/course/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="teacher.course.form.button.create" action="/teacher/course/create"/>
		</jstl:when>
		<jstl:when test="${command != 'create'}">
			<acme:button code="teacher.course.form.button.tutorials" action="/teacher/tutorial/listCourseTutorials?id=${id}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>