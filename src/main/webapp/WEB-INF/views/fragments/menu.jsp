<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.daniel-favourite-link" action="http://www.twitter.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.raul-favourite-link" action="http://www.twitch.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.any.user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.blinks" action="/any/blink/list"/>
			<acme:menu-suboption code="master.menu.any.tutorials" action="/any/tutorial/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.posts" action="/authenticated/post/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.teacher" access="hasRole('Teacher')">
			<acme:menu-suboption code="master.menu.teacher.tutorials" action="/teacher/tutorial/list" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.learner" access="hasRole('Learner')">
			<acme:menu-suboption code="master.menu.learner.help-requests" action="/learner/help-request/list" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.learner.learner-dashboard" action="/learner/learner-dashboard/show"/>
			<acme:menu-separator/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

