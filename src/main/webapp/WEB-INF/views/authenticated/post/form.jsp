<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.post.form.label.caption" path="caption"/>
	<acme:input-textbox code="authenticated.post.form.label.instantation-moment" path="instantationMoment"/>	
	<acme:input-textarea code="authenticated.post.form.label.message" path="message"/>
	<acme:input-textarea code="authenticated.post.form.label.info" path="info"/>	
	<acme:input-textbox code="authenticated.post.form.label.flag" path="flag"/>
</acme:form>