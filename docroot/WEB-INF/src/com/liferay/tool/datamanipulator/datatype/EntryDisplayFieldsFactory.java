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

package com.liferay.tool.datamanipulator.datatype;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.datatype.blogs.BlogsDisplayFields;
import com.liferay.tool.datamanipulator.datatype.bookmarks.BookmarksDisplayFields;
import com.liferay.tool.datamanipulator.datatype.calendar.CalendarDisplayFields;
import com.liferay.tool.datamanipulator.datatype.categories.CategoriesDisplayFields;
import com.liferay.tool.datamanipulator.datatype.documenlibrary.DocumentLibraryDisplayFields;
import com.liferay.tool.datamanipulator.datatype.journal.JournalDisplayFields;
import com.liferay.tool.datamanipulator.datatype.layout.LayoutDisplayFields;
import com.liferay.tool.datamanipulator.datatype.messageboards.MessageBoardsDisplayFields;
import com.liferay.tool.datamanipulator.datatype.organization.OrganizationDisplayFields;
import com.liferay.tool.datamanipulator.datatype.wiki.WikiDisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public final class EntryDisplayFieldsFactory {

	public static List<Field> getDisplayFields(String entryTypeKey)
		throws PortalException, SystemException {

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_ASSET_CATEGORIES)) {
			return CategoriesDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_BLOGS)) {
			return BlogsDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_BOOKMARKS)) {
			return BookmarksDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_CALENDAR)) {
			return CalendarDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA)) {
			return DocumentLibraryDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_JOURNAL)) {
			return JournalDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_LAYOUT)) {
			return LayoutDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_MESSAGE_BOARDS)) {
			return MessageBoardsDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_WIKI)) {
			return WikiDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.PORTAL_ORGANIZATION)) {
			return OrganizationDisplayFields.getDisplayFields();
		}

		return null;
	}

}