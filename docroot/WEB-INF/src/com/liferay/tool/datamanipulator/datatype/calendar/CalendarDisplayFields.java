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

package com.liferay.tool.datamanipulator.datatype.calendar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class CalendarDisplayFields {
	public static List<Field> getDisplayFields() throws SystemException {
		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_CALENDAR_EVENT, true);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_CALENDAR_EVENT);
		fields.addSeparator();

		fields.addLabel("start-date");
		fields.addDate("start-date");
		fields.addLabel("end-date");
		fields.addDate("end-date");
		fields.addTime("duration");
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}