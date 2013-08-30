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

import java.io.File;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
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
public class DocumentLibraryFileHandler extends AbstractEntryHandler
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
	public DocumentLibraryFileHandler(
			int count, int update, int depth, int subCount, BaseEntry baseEntry,
			EntryHandlerModel subEntryHandler,
			RequestProcessor requestProcessor)
		throws SystemException {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);

		_file = requestProcessor.getFile("upload-file");
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("folderId", parentId);
		args.setParameter("name", "Test DL File" + postString + " Name");
		args.setParameter("title", "Test DL File" + postString + " Title");
		args.setParameter(
			"description", "Test DL File" + postString + " Description");

		args.setParameter("file", _file);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */
	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((DLFileEntry)createdEntry).getGroupId(),
			DLFileEntry.class.getName(),
			((DLFileEntry)createdEntry).getFileEntryId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
			entryId);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("folderId", fileEntry.getFolderId());
		args.setParameter("name", fileEntry.getName());
		args.setParameter("sourceFileName", _file.getName());
		args.setParameter("file", _file);
		args.setParameter(
			"title", EntryUtil.getEditString(fileEntry.getTitle(), postString));

		args.setParameter(
			"description",
			EntryUtil.getEditString(fileEntry.getDescription(), postString));

		args.setParameter("majorVersion", false);

		return args;
	}

	private File _file;

}