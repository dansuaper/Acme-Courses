<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" aria-describedby="Dashboard">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-theory-tutorials"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTheoryTutorials}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-cost-of-theory-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageCostOfTheoryTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-cost-of-theory-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationCostOfTheoryTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-cost-of-theory-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minimumCostOfTheoryTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-cost-of-theory-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maximumCostOfTheoryTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-lab-tutorials"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfLabTutorials}"/>
		</td>
	</tr>
	
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-cost-of-lab-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageCostOfLabTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-cost-of-lab-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationCostOfLabTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-cost-of-lab-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minimumCostOfLabTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-cost-of-lab-turorials-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maximumCostOfLabTutorialsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-help-requests-by-status"/>
		</th> 
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="totalNumberOfHelpRequestsByStatus"></canvas>
			</div>
		</td>
	</tr>

	
	<jstl:forEach items="${averageBudgetOfHelpRequestsByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${averageBudgetOfHelpRequestsByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="administrator.dashboard.form.status.average.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${deviationBudgetOfHelpRequestsByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${deviationBudgetOfHelpRequestsByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="administrator.dashboard.form.status.deviation.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${minimunBudgetOfHelpRequestsByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${minimunBudgetOfHelpRequestsByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="administrator.dashboard.form.status.minimum.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${maximumBudgetOfHelpRequestsByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${maximumBudgetOfHelpRequestsByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="administrator.dashboard.form.status.maximum.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
		
</table>

<script type="text/javascript">
$(document).ready(function() {
	
	function newChart(labels, data, id) {
	
		var data = {
			labels : labels,
			datasets : [
				{
					data : data
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById(id);
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	
	}
	
	var averageIngredients = {
		<jstl:forEach items="${averageCostOfTheoryTutorialsByCurrency}" var="item" varStatus="loop">
 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
		</jstl:forEach>
	};
	newChart(Object.keys(averageTheoryTutorials), Object.values(averageTheoryTutorials), "averageCostOfTheoryTutorialsByCurrency");
	
	var deviationIngredients = {
			<jstl:forEach items="${deviationCostOfTheoryTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(deviationTheoryTutorials), Object.values(deviationTheoryTutorials), "deviationCostOfTheoryTutorialsByCurrency");
	
	var minimumIngredients = {
			<jstl:forEach items="${minimumCostOfTheoryTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(minimumTheoryTutorials), Object.values(minimumTheoryTutorials), "minimumCostOfTheoryTutorialsByCurrency");
		
	var maximumIngredients = {
			<jstl:forEach items="${maximumCostOfTheoryTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(maximumTheoryTutorials), Object.values(maximumTheoryTutorials), "maximumCostOfTheoryTutorialsByCurrency");
	
	var averageKitchenUtensils = {
			<jstl:forEach items="${averageCostOfLabTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(averageLabTutorials), Object.values(averageLabTutorials), "averageCostOfLabTutorialsByCurrency");
			
	var deviationKitchenUtensils = {
			<jstl:forEach items="${deviationCostOfLabTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(deviationLabTutorials), Object.values(deviationLabTutorials), "deviationCostOfLabTutorialsByCurrency");
			
	var minimumKitchenUtensils = {
			<jstl:forEach items="${minimumCostOfLabTutorialsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(minimumLabTutorials), Object.values(minimumLabTutorials), "minimumCostOfLabTutorialsByCurrency");
				
	var maximumKitchenUtensils = {
			<jstl:forEach items="${maximumRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(maximumLabTutorials), Object.values(maximumLabTutorials), "maximumCostOfLabTutorialsByCurrency");
	
	var totalFineDishes = {
			<jstl:forEach items="${totalNumberOfFineDishesByStatus}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(totalHelpRequests), Object.values(totalHelpRequests), "totalNumberOfHelpRequestsByStatus");
	});
</script>

<acme:return/>