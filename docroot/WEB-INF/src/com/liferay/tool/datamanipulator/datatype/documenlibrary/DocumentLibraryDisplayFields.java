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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class DocumentLibraryDisplayFields {
	public static final String DOCUMENTS_AND_MEDIA_FOLDER_ID =
		"documents-and-media-folder-id";

	public static final String DOCUMENTS_AND_MEDIA_FOLDER_LIST =
		"documents-and-media-folder-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<DLFolder> dlFolderList =
			DLFolderLocalServiceUtil.getFolders(
				groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		List<KeyValuePair> dlFolderNameId = new ArrayList<KeyValuePair>(
			dlFolderList.size() + 1);

		dlFolderNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)));

		for (DLFolder folder : dlFolderList) {
			dlFolderNameId.add(
				new KeyValuePair(
					folder.getName(), String.valueOf(folder.getFolderId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addFile("upload-file", true);
		fields.addSeparator();

		fields.addSelectList(DOCUMENTS_AND_MEDIA_FOLDER_LIST, dlFolderNameId);
		fields.addInput(
			DOCUMENTS_AND_MEDIA_FOLDER_ID,
			String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));

		fields.addInfo(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA + "-add-to-exist");
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addDepth(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addSubCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addToParent(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}