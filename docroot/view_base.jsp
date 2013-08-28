<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@include file="/init.jsp" %>

<liferay-ui:success key="dataGenerated" message="data-generated-successfully" />
<liferay-ui:error key="errorOccurred" message="error-occurred" />

<%
String cmd = ParamUtil.getString(request, Constants.CMD, Constants.VIEW);

String currentURL = PortalUtil.getCurrentURL(request);

String entryType = ParamUtil.getString(request, "entryType");

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/admin/view");
portletURL.setParameter(Constants.CMD, Constants.EDIT);
portletURL.setParameter("redirect", backURL);
%>

<%@ include file="/main_navigation.jspf" %>

<c:choose>
	<c:when test="<%= cmd.equals(Constants.VIEW) %>">
		<%@ include file="/view_summary.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/edit_entry.jsp" %>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />generateEntryType(field) {
		var entryTypeURL = field.value;

		if (entryTypeURL == '') {

			<%
			PortletURL addEntryTypesURL = renderResponse.createRenderURL();

			addEntryTypesURL.setParameter("struts_action", "/admin/view");
			addEntryTypesURL.setParameter(Constants.CMD, Constants.EDIT);
			addEntryTypesURL.setParameter("entryType", entryType);
			%>

			dataTypeURL = '<%= addEntryTypesURL %>';
		}

		location.href = entryTypeURL;
	}
</aui:script>