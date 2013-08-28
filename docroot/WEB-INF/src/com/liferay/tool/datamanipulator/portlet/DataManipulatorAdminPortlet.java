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

package com.liferay.tool.datamanipulator.portlet;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.tool.datamanipulator.datatype.PortletHandlerFactory;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.runner.DataManipulatorRunner;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Tibor Kovács
 *
 */
public class DataManipulatorAdminPortlet extends MVCPortlet {
	public void generateData(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		RequestProcessor requestProcessor = new RequestProcessor(uploadRequest);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String entryType = ParamUtil.getString(uploadRequest, "entryType");

		List<String> portalEntryTypes = EntryTypeKeys.ENTRY_TYPE_PORTAL_LIST;

		long[] groupIds = requestProcessor.getGroupIds();

		if ((groupIds.length <= 0) || portalEntryTypes.contains(entryType)) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		long[] userIds = requestProcessor.getUserIds();

		if ((userIds.length <= 0) || portalEntryTypes.contains(entryType)) {
			userIds = new long[] {themeDisplay.getUserId()};
		}

		for (long groupId : groupIds) {
			for (long userId : userIds) {
				requestProcessor = new RequestProcessor(uploadRequest);

				requestProcessor.setGroupId(groupId);
				requestProcessor.setUserId(userId);

				DataManipulatorRunner runner = new DataManipulatorRunner(
					PortletHandlerFactory.getHandlerInstance(entryType),
					requestProcessor);

				runner.start();
			}
		}
	}

}