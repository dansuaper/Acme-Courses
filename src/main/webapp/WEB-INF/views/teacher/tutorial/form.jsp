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
	<acme:input-url code="teacher.tutorial.form.label.info" path="info"/>	
	<acme:input-textbox code="teacher.tutorial.form.label.teacher" path="teacher.userAccount.username"/>
	<acme:input-textbox code="teacher.tutorial.form.label.published" path="published"/>
</acme:form>