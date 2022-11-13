<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="teacher.course.form.label.ticker" path="ticker"/>	
	<acme:input-textarea code="teacher.course.form.label.caption" path="caption"/>	
	<acme:input-textarea code="teacher.course.form.label.abstract-text" path="abstractText"/>
	<acme:input-textbox code="teacher.course.form.label.link" path="link"/>	
	<acme:input-textbox code="teacher.course.form.label.published" path="published"/>	
	<acme:input-textbox code="teacher.course.form.label.cost" path="cost"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="teacher.course.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="teacher.course.form.label.teacher" path="teacher.userAccount.username"/>
	<acme:button code="teacher.course.form.button.tutorials" action="/teacher/tutorial/listCourseTutorials?id=${id}"/>
</acme:form>