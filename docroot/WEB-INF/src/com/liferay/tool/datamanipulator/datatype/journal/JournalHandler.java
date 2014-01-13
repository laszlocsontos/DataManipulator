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

package com.liferay.tool.datamanipulator.datatype.journal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil;
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
public class JournalHandler extends AbstractPortletHandler implements
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

		// Journal Article

		int articleCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);

		int articleUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);

		EntryTypeReader articleEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);

		BaseEntry articleEntry = new BaseEntry(articleEntryType);

		EntryHandlerModel articleHandler = new JournalArticleHandler(
			articleCount, articleUpdate, 0, 0, articleEntry, null,
			requestProcessor);

		// Journal Folder

		int folderCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_JOURNAL_FOLDER);

		int folderUpdate = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_JOURNAL_FOLDER);

		int folderDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_JOURNAL_FOLDER);

		int folderSubCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_JOURNAL_FOLDER);

		EntryTypeReader folderType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_JOURNAL_FOLDER);

		BaseEntry folderEntry = new BaseEntry(folderType);

		EntryHandlerModel folderEntryHandler = new JournalFolderHandler(
			folderCount, folderUpdate, folderDepth, folderSubCount, folderEntry,
			articleHandler, requestProcessor);

		long parentFolderId = requestProcessor.getLong(
			JournalDisplayFields.JOURNAL_FOLDER_ID);

		if (parentFolderId == 0) {
			parentFolderId = requestProcessor.getLong(
			JournalDisplayFields.JOURNAL_FOLDER_LIST);
		}

		JournalFolder folder = JournalFolderLocalServiceUtil.fetchFolder(
			parentFolderId);

		if (Validator.isNotNull(folder)) {
			folderEntryHandler.generateEntries(parentFolderId);
		}
		else {
			folderEntryHandler.generateEntries((long)0);
		}
	}

}