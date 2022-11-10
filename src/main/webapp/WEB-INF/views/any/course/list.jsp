<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.course.list.label.ticker" path="ticker" width="30%"/>
	<acme:list-column code="any.course.list.label.caption" path="caption" width="30%"/>
	<acme:list-column code="any.course.list.label.link" path="link" width="40%"/>
</acme:list>