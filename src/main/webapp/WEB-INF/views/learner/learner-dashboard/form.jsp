<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

	<acme:message code="learner.learner-dashboard.form.label.avg-title"/>	
	<table class="table table-sm">	
		<caption></caption>
		
		
	<jstl:if test="${empty averageBudgetByCurrency}">
	<acme:message code="learner.learner-dashboard.form.label.nohayPeticionesAyuda"/>	
	<br>
	<br>
	</jstl:if>	
		<jstl:forEach items="${averageBudgetByCurrency}" var="entry"> 	
		<tr>	
			<th id="">	
			<acme:message code="learner.learner-dashboard.form.label.average-sentence"/>		
			<jstl:set var = "string1" value = "${entry.key}"/>
			<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
			<acme:print value="${string2[0]}"/>
			<acme:message code="learner.learner-dashboard.form.label.and-status"/>
			<acme:print value="${string2[1]}"/>
			<acme:message code="learner.learner-dashboard.form.label.colon"/>
			</th>
			<td style= "text-align:right;">
				<acme:print value="${entry.value}"/>
				
			</td>		
		</tr>
		</jstl:forEach>
	</table>
	
	<acme:message code="learner.learner-dashboard.form.label.deviationTitle"/>		
	<table class="table table-sm">
		<caption></caption>
		
	<jstl:if test="${empty deviationBudgetByCurrency}">
	<acme:message code="learner.learner-dashboard.form.label.nohayPeticionesAyuda"/>	
	<br>
	<br>
	</jstl:if>
		<jstl:forEach items="${deviationBudgetByCurrency}" var="entry"> 
		<tr>	
			<th id="">
				<acme:message code="learner.learner-dashboard.form.label.deviation-sentence"/>		
				<jstl:set var = "string1" value = "${entry.key}"/>
				<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
				<acme:print value="${string2[0]}"/>
				<acme:message code="learner.learner-dashboard.form.label.and-status"/>		
				<acme:print value="${string2[1]}"/>
				<acme:message code="learner.learner-dashboard.form.label.colon"/>		
			</th>
			<td style= "text-align:right;">
				<acme:print value="${entry.value}"/>
			</td>		
		</tr>
		</jstl:forEach>
	</table>
	
	
	<acme:message code="learner.learner-dashboard.form.label.minTitle"/>
	<table class="table table-sm">	
		<caption></caption>
	<jstl:if test="${empty minBudgetByCurrency}">
	<acme:message code="learner.learner-dashboard.form.label.nohayPeticionesAyuda"/>	
	<br>
	<br>
	</jstl:if>
		<jstl:forEach items="${minBudgetByCurrency}" var="entry"> 
			<tr>	
				<th id="">
					<acme:message code="learner.learner-dashboard.form.label.min-sentence"/>		
					<jstl:set var = "string1" value = "${entry.key}"/>
					<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
					<acme:print value="${string2[0]}"/>
					<acme:message code="learner.learner-dashboard.form.label.and-status"/>		
					<acme:print value="${string2[1]}"/>
					<acme:message code="learner.learner-dashboard.form.label.colon"/>		
				</th>
				<td style= "text-align:right;">
					<acme:print value="${entry.value}"/>
				</td>		
			</tr>
		</jstl:forEach>
	</table>		
	<acme:message code="learner.learner-dashboard.form.label.maxTitle"/>		
	<table class="table table-sm">	
		<caption></caption>
	<jstl:if test="${empty maxBudgetByCurrency}">
	<acme:message code="learner.learner-dashboard.form.label.nohayPeticionesAyuda"/>	
	<br>
	<br>
	</jstl:if>
		<jstl:forEach items="${maxBudgetByCurrency}" var="entry"> 
			<tr>	
				<th id="">
					<acme:message code="learner.learner-dashboard.form.label.max-sentence"/>		
					<jstl:set var = "string1" value = "${entry.key}"/>
					<jstl:set var = "string2" value = "${fn:split(string1, '->')}" />
					<acme:print value="${string2[0]}"/>
					<acme:message code="learner.learner-dashboard.form.label.and-status"/>		
					<acme:print value="${string2[1]}"/>
					<acme:message code="learner.learner-dashboard.form.label.colon"/>		
				</th>
				<td style= "text-align:right;">
					<acme:print value="${entry.value}"/>
				</td>		
			</tr>
		</jstl:forEach>
	</table>
	<acme:message code="learner.learner-dashboard.form.label.absoluteTitle"/>		
	<table class="table table-sm">
		<caption></caption>
			<tr>	
				<th id="">
					<acme:message code="learner.learner-dashboard.form.label.totalNumberOfProposedHelpRequests"/>		
		
				</th>
				<td style= "text-align:right;">
					<acme:print value="${totalNumberOfProposedHelpRequests}"/>
				</td>		
			</tr>
			<tr>	
				<th id="">
					<acme:message code="learner.learner-dashboard.form.label.totalNumberOfAcceptedHelpRequests"/>
				</th>
				<td style= "text-align:right;">
					<acme:print value="${totalNumberOfAcceptedHelpRequests}"/>
				</td>		
			</tr>
			<tr>	
				<th id="">
					<acme:message code="learner.learner-dashboard.form.label.totalNumberOfDeniedHelpRequests"/>		
				</th>
				<td style= "text-align:right;">
					<acme:print value="${totalNumberOfDeniedHelpRequests}"/> 
				</td>		
			</tr>
	</table>