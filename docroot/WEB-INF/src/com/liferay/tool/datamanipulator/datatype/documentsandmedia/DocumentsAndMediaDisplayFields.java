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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class DocumentsAndMediaDisplayFields {
	public static List<Field> getDisplayFields() throws SystemException {
		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addFile("upload-file", true);
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

		fields.addInput("repository-id");
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}