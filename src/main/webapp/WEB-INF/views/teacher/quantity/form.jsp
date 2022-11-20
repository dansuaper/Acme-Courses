<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-integer code="teacher.quantity.form.label.amount" path="amount"/>
		<acme:input-textbox code="teacher.quantity.form.label.time-unit" path="timeUnit"/>	
		<acme:input-textbox code="teacher.tutorial.form.label.type" path="tutorial.type" readonly="true"/>
		<acme:input-textbox code="teacher.tutorial.form.label.title" path="tutorial.title" readonly="true"/>
		<acme:input-textbox code="teacher.tutorial.form.label.ticker" path="tutorial.ticker" readonly="true"/>
		<acme:input-money code="teacher.tutorial.form.label.cost" path="tutorial.cost" readonly="true" />
		<jstl:if test="${differentCurrency == true}">
			<acme:input-money code="teacher.tutorial.form.label.convertir" path="convertir" readonly="true"/>
		</jstl:if>
		<acme:input-textbox code="teacher.course.form.label.ticker" path="course.ticker" readonly="true"/>
		</jstl:if>		
			<jstl:if test="${coursePublished == false}">
				<acme:submit code="teacher.quantity.form.button.update" action="/teacher/quantity/update" />
				<acme:submit code="teacher.quantity.form.button.delete" action="/teacher/quantity/delete" />
			</jstl:if>
		<jstl:if test="${command == 'create'}">
			<acme:input-integer code="teacher.quantity.form.label.amount" path="amount"/>
			<acme:input-textbox code="teacher.quantity.form.label.time-unit" path="timeUnit"/>
			<acme:input-select code="teacher.quantity.form.label.tutorial" path="tutorialId">
				<jstl:forEach items="${tutorials}" var="tutorial">
					<acme:input-option code="${tutorial.getTitle()}" value="${tutorial.getId()}" selected="${tutorial.getId() == tutorialId}" />
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="tutorial.quantity.form.button.create" action="/tutorial/quantity/create?masterId=${masterId}" />
		</jstl:if>
</acme:form>