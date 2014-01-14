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

package com.liferay.tool.datamanipulator.datatype.wiki;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
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
public class WikiDisplayFields {
	public static final String WIKI_NODE_ID = "wiki-node-id";
	public static final String WIKI_NODE_LIST = "wiki-node-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException, PortalException {

		List<WikiNode> wikiNodeList = WikiNodeLocalServiceUtil.getNodes(
			groupId);

		List<KeyValuePair> wikiNodeNameId = new ArrayList<KeyValuePair>(
			wikiNodeList.size() + 1);

		wikiNodeNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_WIKI + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT, "0"));

		for (WikiNode node : wikiNodeList) {
			wikiNodeNameId.add(
				new KeyValuePair(
					node.getName(), String.valueOf(node.getNodeId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_WIKI_NODE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_WIKI_NODE);
		fields.addSeparator();

		fields.addSelectList(WIKI_NODE_LIST, wikiNodeNameId);
		fields.addInput(WIKI_NODE_ID);
		fields.addInfo(EntryTypeKeys.GENERAL_WIKI + "-ad-to-exist");
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addDepth(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addSubCount(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addHidden(
			EntryTypeKeys.GENERAL_WIKI_PAGE + StringPool.DASH +
				FieldKeys.ADD_TO_PARENT, FieldKeys.ADD_TO_ALL_PARENT);

		fields.addSeparator();

		return fields.getDisplayFields();
	}

}