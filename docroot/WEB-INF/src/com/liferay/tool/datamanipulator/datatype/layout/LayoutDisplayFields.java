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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
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
public class LayoutDisplayFields {
	public static final String PRIVATE_LAYOUT_ID = "private-layout-id";
	public static final String PRIVATE_LAYOUT_LIST = "private-layout-list";
	public static final String PUBLIC_LAYOUT_ID = "public-layout-id";
	public static final String PUBLIC_LAYOUT_LIST = "public-layout-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<Layout> publicLayoutList = LayoutLocalServiceUtil.getLayouts(
			groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		List<KeyValuePair> publicLayoutNameId = new ArrayList<KeyValuePair>(
			publicLayoutList.size() + 1);

		publicLayoutNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_LAYOUT + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(LayoutConstants.DEFAULT_PARENT_LAYOUT_ID)));

		for (Layout layout : publicLayoutList) {
			publicLayoutNameId.add(
				new KeyValuePair(
					layout.getName(), String.valueOf(layout.getPlid())));
		}

		List<Layout> privateLayoutList = LayoutLocalServiceUtil.getLayouts(
				groupId, true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		List<KeyValuePair> privateLayoutNameId = new ArrayList<KeyValuePair>(
			privateLayoutList.size() + 1);

		privateLayoutNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_LAYOUT + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(LayoutConstants.DEFAULT_PARENT_LAYOUT_ID)));

		for (Layout layout : privateLayoutList) {
			privateLayoutNameId.add(
				new KeyValuePair(
					layout.getName(), String.valueOf(layout.getPlid())));
		}

		List<KeyValuePair> portletKVP = new ArrayList<KeyValuePair>();

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			if (!portlet.isSystem() &&
				!portlet.isAddDefaultResource() &&
				(portlet.getControlPanelEntryCategory() == StringPool.BLANK)) {

				portletKVP.add(
					new KeyValuePair(
						portlet.getDisplayName(), portlet.getPortletId()));
			}
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addSelectList(PUBLIC_LAYOUT_LIST, publicLayoutNameId);
		fields.addInput(PUBLIC_LAYOUT_ID);
		fields.addInfo(EntryTypeKeys.GENERAL_LAYOUT + "-add-to-exist-public");
		fields.addSelectList(PRIVATE_LAYOUT_LIST, privateLayoutNameId);
		fields.addInput(PRIVATE_LAYOUT_ID);
		fields.addInfo(EntryTypeKeys.GENERAL_LAYOUT + "-add-to-exist-private");
		fields.addInfo(
			EntryTypeKeys.GENERAL_LAYOUT + "-public-and-private-added");

		fields.addSeparator();

		fields.addCheckbox(EntryTypeKeys.GENERAL_LAYOUT_ENTRY);
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