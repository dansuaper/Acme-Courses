<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.post.form.label.caption" path="caption"/>
	<acme:input-textarea code="administrator.post.form.label.message" path="message"/>
	<acme:input-url code="administrator.post.form.label.info" path="info" placeholder="https://google.com"/>	
	<acme:input-checkbox code="administrator.post.form.label.flag" path="flag"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="administrator.post.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.post.form.button.create" action="/administrator/post/create"/>
	</jstl:if>
		
	
</acme:form>