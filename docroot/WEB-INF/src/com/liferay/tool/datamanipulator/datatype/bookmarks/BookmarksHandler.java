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

package com.liferay.tool.datamanipulator.datatype.bookmarks;

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
public class BookmarksHandler extends AbstractPortletHandler implements
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

		// Bookmarks Entry

		int entryCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		int entryUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		EntryTypeReader entryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		BaseEntry entry = new BaseEntry(entryType);

		EntryHandlerModel entryhandler = new BookmarksEntryHandler(
			entryCount, entryUpdate, 0, 0, entry, null, requestProcessor);

		// Bookmarks Folder

		int folderCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		EntryTypeReader folderEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		BaseEntry folderEntry = new BaseEntry(folderEntryType);

		EntryHandlerModel folderEntryhandler = new BookmarksFolderHandler(
			folderCount, folderUpdate, folderDepth, folderSubCount,
			folderEntry, entryhandler, requestProcessor);

		folderEntryhandler.generateEntries((long)0);
	}

}