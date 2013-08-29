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
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
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
public class DocumentLibraryFolderHandler extends AbstractEntryHandler
		implements EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param subEntryHandler
	 * @param requestProcessor
	 * @throws SystemException 
	 */
	public DocumentLibraryFolderHandler(
			int count, int update, int depth, int subCount, BaseEntry baseEntry,
			EntryHandlerModel subEntryHandler,
			RequestProcessor requestProcessor)
		throws SystemException {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("parentFolderId", parentId);
		args.setParameter("name", "Test DL Folder" + postString + " Name");
		args.setParameter(
			"description", "Test DL Folder" + postString + " Description");

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */
	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((DLFolder)createdEntry).getGroupId(), DLFolder.class.getName(),
			((DLFolder)createdEntry).getFolderId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		DLFolder folder = DLFolderLocalServiceUtil.getFolder(entryId);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("folderId", entryId);
		args.setParameter("parentFolderId", folder.getParentFolderId());
		args.setParameter(
			"name", EntryUtil.getEditString(folder.getName(), postString));

		args.setParameter(
			"description",
			EntryUtil.getEditString(folder.getDescription(), postString));

		return args;
	}

}