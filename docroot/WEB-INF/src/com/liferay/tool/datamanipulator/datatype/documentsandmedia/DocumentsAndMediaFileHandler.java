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

package com.liferay.tool.datamanipulator.datatype.documentsandmedia;

import java.io.File;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Repository;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
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
public class DocumentsAndMediaFileHandler extends AbstractEntryHandler
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
	public DocumentsAndMediaFileHandler(
			int count, int update, int depth, int subCount, BaseEntry baseEntry,
			EntryHandlerModel subEntryHandler,
			RequestProcessor requestProcessor)
		throws SystemException {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);

		_file = requestProcessor.getFile("upload-file");

		_repositoryId = requestProcessor.getLong("repository-id");

		Repository repo = RepositoryLocalServiceUtil.fetchRepository(
			_repositoryId);

		if (Validator.isNull(repo) || (_repositoryId == 0)) {
			_repositoryId = requestProcessor.getGroupId();
		}
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter(
			"description", "Test DM File" + postString + " Description");

		args.setParameter("file", _file);
		args.setParameter("folderId", parentId);
		args.setParameter("mimeType", MimeTypesUtil.getContentType(_file));
		args.setParameter("repositoryId", _repositoryId);
		args.setParameter("sourceFileName", _file.getName());
		args.setParameter("title", "Test DM File" + postString + " Title");

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */
	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((FileEntry)createdEntry).getGroupId(), FileEntry.class.getName(),
			((FileEntry)createdEntry).getFileEntryId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(entryId);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("fileEntryId", entryId);
		args.setParameter("file", null);
		args.setParameter(
			"title", EntryUtil.getEditString(fileEntry.getTitle(), postString));

		args.setParameter(
			"description",
			EntryUtil.getEditString(fileEntry.getDescription(), postString));

		args.setParameter("majorVersion", false);
		args.setParameter("mimeType", MimeTypesUtil.getContentType(_file));
		args.setParameter("repositoryId", _repositoryId);
		args.setParameter("sourceFileName", _file.getName());

		return args;
	}

	private long _repositoryId;
	private File _file;

}