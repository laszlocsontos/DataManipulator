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

package com.liferay.tool.datamanipulator.datatype.messageboards;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.util.EntryReaderUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.handler.portlethandler.AbstractPortletHandler;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public class MessageBoardsHandler extends AbstractPortletHandler implements
		PortletHandlerModel {

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startErase(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public void startErase(RequestProcessor requestProcessor)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startGenerate(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public void startGenerate(RequestProcessor requestProcessor)
			throws PortalException, SystemException {

		// MessageBoards Message

		int messageCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		int messageUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		EntryTypeReader messageEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		BaseEntry messageEntry = new BaseEntry(messageEntryType);

		EntryHandlerModel messageHandler = new MBMessageHandler(
			messageCount, messageUpdate, 0, 0, messageEntry, null,
			requestProcessor);

		// MessageBoards Thread

		int threadCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);

		int threadUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);

		EntryTypeReader threadEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		BaseEntry threadEntry = new BaseEntry(threadEntryType);

		EntryHandlerModel threadHandler = new MBThreadHandler(
			threadCount, threadUpdate, 0, 0, threadEntry, messageHandler,
			requestProcessor);

		// MessageBoards Category

		int categoryCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categoryUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categoryDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categorySubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		EntryTypeReader categoryEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		BaseEntry categoryEntry = new BaseEntry(categoryEntryType);

		EntryHandlerModel categoryHandler = new MBCategoryHandler(
			categoryCount, categoryUpdate, categoryDepth, categorySubCount,
			categoryEntry, threadHandler, requestProcessor);

		// Start create

		categoryHandler.generateEntries((long)0);
	}

}