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

package com.liferay.tool.datamanipulator.datatype.layout;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

/**
 * @author Tibor Kovács
 *
 */
public class LayoutEntryHandler extends AbstractEntryHandler implements
		EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param subEntryHandler
	 * @param requestProcessor
	 */
	public LayoutEntryHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);

		_portletCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_LAYOUT_PORTLET);

		_portletIds = requestProcessor.getStringValues("portlet");

		_userId = requestProcessor.getUserId();
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		long parentLayoutId = parentId;

		if (parentLayoutId != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			Layout layout = LayoutLocalServiceUtil.getLayout(parentId);

			parentLayoutId = layout.getLayoutId();
		}

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("privateLayout", false);
		args.setParameter("parentLayoutId", parentLayoutId);
		args.setParameter("name", "Test Page" + postString + " Name");
		args.setParameter("title", "Test Page" + postString + " Title");
		args.setParameter("type", LayoutConstants.TYPE_PORTLET);
		args.setParameter("hidden", false);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */
	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		Layout layout = (Layout)createdEntry;

		String[] portletIds = new String[_portletCount];

		for (int i = 0; i < _portletCount; i++) {
			int index = EntryUtil.nextInt(_portletIds.length);

			portletIds[i] = _portletIds[index];
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.addPortletIds(_userId, portletIds, false);

		layoutTypePortlet.resetModes();
		layoutTypePortlet.resetStates();

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getLayoutId(), layout.getTypeSettings());

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			layout.getGroupId(), Layout.class.getName(),
			layout.getPlid());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		return null;
	}

	private int _portletCount;
	private String[] _portletIds;
	private long _userId;

}