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

<%@ page import="com.liferay.tool.datamanipulator.entry.EntryTypeKeys"%>
<%@ page import="java.util.ArrayList" %>

<%@include file="/init.jsp" %>

<%
List<String> entryTypeNavigation = new ArrayList<String>();

entryTypeNavigation.add(EntryTypeKeys.ENTRY_TYPE_GENERAL);
entryTypeNavigation.add(EntryTypeKeys.ENTRY_TYPE_PLUGIN);
%>

<%@include file="/view_base.jsp" %>