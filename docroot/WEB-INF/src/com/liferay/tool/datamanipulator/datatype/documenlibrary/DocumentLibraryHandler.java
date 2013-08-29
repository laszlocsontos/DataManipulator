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

package com.liferay.tool.datamanipulator.datatype.documenlibrary;

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
public class DocumentLibraryHandler extends AbstractPortletHandler implements
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

		// Documents And Media File Entry

		int fileCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		int fileUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		EntryTypeReader fileEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		BaseEntry fileEntry = new BaseEntry(fileEntryType);

		EntryHandlerModel fileEntryHandler = new DocumentLibraryFileHandler(
			fileCount, fileUpdate, 0, 0, fileEntry, null, requestProcessor);

		// Documents And Media Folder

		int folderCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		EntryTypeReader folderEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		BaseEntry folderEntry = new BaseEntry(folderEntryType);

		EntryHandlerModel folderHandler = new DocumentLibraryFolderHandler(
			folderCount, folderUpdate, folderDepth, folderSubCount, folderEntry,
			fileEntryHandler, requestProcessor);

		folderHandler.generateEntries((long)0);
	}

}