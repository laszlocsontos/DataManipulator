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

package com.liferay.tool.datamanipulator.datatype.categories;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class CategoriesDisplayFields {
	public static List<Field> getDisplayFields() throws SystemException {
		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_ASSET_VOCABULARY, true);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_ASSET_VOCABULARY);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addDepth(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addSubCount(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addHidden(FieldKeys.ADD_TO_PARENT, FieldKeys.ADD_TO_ALL_PARENT);

		fields.addSeparator();

		return fields.getDisplayFields();
	}

}