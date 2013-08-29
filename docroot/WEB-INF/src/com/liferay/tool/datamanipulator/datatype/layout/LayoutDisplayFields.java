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

package com.liferay.tool.datamanipulator.datatype.layout;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class LayoutDisplayFields {
	public static List<Field> getDisplayFields() throws SystemException {
		List<KeyValuePair> portletKVP = new ArrayList<KeyValuePair>();

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			if (!portlet.isSystem() &&
				!portlet.isAddDefaultResource() &&
				(portlet.getControlPanelEntryCategory() == null)) {

				portletKVP.add(
					new KeyValuePair(
						portlet.getDisplayName(), portlet.getPortletId()));
			}
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_LAYOUT_ENTRY, true);
		fields.addDepth(EntryTypeKeys.GENERAL_LAYOUT_ENTRY);
		fields.addSubCount(EntryTypeKeys.GENERAL_LAYOUT_ENTRY);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_LAYOUT_PORTLET);
		fields.addMultiSelectList("portlet", portletKVP);
		fields.addToParent(EntryTypeKeys.GENERAL_LAYOUT_PORTLET);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}