<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.blink.form.label.caption" path="caption"/>
	<acme:input-textbox code="any.blink.form.label.author-alias" path="authorAlias"/>	
	<acme:input-textarea code="any.blink.form.label.message" path="message"/>	
	<acme:input-email code="any.blink.form.label.email" path="email" placeholder="example@gmail.com" />	
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="any.blink.form.label.confirmation" path="confirmation"/>
		<acme:submit code="any.blink.form.button.create" action="/any/blink/create"/>
	</jstl:if>
		
</acme:form>