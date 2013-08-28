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

package com.liferay.tool.datamanipulator.displayfield;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class DisplayFields {
	public DisplayFields() {
		_displayFields = new ArrayList<Field>();
		_index = -1;
	}

	public void addCheckbox(String key) {
		_put(key, FieldTypeKeys.CHECK_BOX);
	}

	public void addCount(String key) {
		addCount(key, false);
	}

	public void addCount(String key, boolean required) {
		key = key + StringPool.DASH + FieldKeys.COUNT;

		if (required) {
			_put(key, FieldTypeKeys.INPUT_REQUIRED);
		}
		else {
			_put(key, FieldTypeKeys.INPUT);
		}
	}

	public void addDate(String key) {
		_put(key, FieldTypeKeys.DATE);
	}

	public void addDepth(String key) {
		addDepth(key, false);
	}

	public void addDepth(String key, boolean required) {
		key = key + StringPool.DASH + FieldKeys.DEPTH;

		if (required) {
			_put(key, FieldTypeKeys.INPUT_REQUIRED);
		}
		else {
			_put(key, FieldTypeKeys.INPUT);
		}
	}

	public void addFile(String key) {
		addFile(key, false);
	}

	public void addFile(String key, boolean required) {
		if (required) {
			_put(key, FieldTypeKeys.FILE_REQUIRED);
		}
		else {
			_put(key, FieldTypeKeys.FILE);
		}
	}

	public void addHidden(String key) {
		_put(key, FieldTypeKeys.HIDDEN);
	}

	public void addHidden(String key, Object value) {
		_put(key, FieldTypeKeys.HIDDEN, value);
	}

	public void addInput(String key) {
		addInput(key, StringPool.BLANK, false);
	}

	public void addInput(String key, boolean required) {
		addInput(key, StringPool.BLANK, required);
	}

	public void addInput(String key, String value) {
		addInput(key, value, false);
	}

	public void addInput(String key, String value, boolean required) {
		if (required) {
			_put(key, FieldTypeKeys.INPUT_REQUIRED, value);
		}
		else {
			_put(key, FieldTypeKeys.INPUT, value);
		}
	}

	public void addLabel(String key) {
		_put(key, FieldTypeKeys.LABEL);
	}

	public void addMultiSelectList(String key, List<KeyValuePair> values) {
		_put(key, FieldTypeKeys.MULTI_SELECT_LIST, values);
	}

	public void addOrganizationMultiSelect() throws SystemException {
		List<Organization> orgs =
			OrganizationLocalServiceUtil.getOrganizations(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(orgs.size());

		for (Organization org : orgs) {
			values.add(
				new KeyValuePair(
					org.getName(), String.valueOf(org.getOrganizationId())));
		}

		addMultiSelectList(
			FieldTypeKeys.MULTI_SELECT_ORGANIZATION_LIST, values);
	}

	public void addRoleMultiSelect() throws SystemException {
		List<Role> roles = RoleLocalServiceUtil.getRoles(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(roles.size());

		for (Role role : roles) {
			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				values.add(
					new KeyValuePair(
						role.getName(), String.valueOf(role.getRoleId())));
			}
		}

		addMultiSelectList(FieldTypeKeys.MULTI_SELECT_ROLE_LIST, values);
	}

	public void addSelectList(String key, List<KeyValuePair> values) {
		_put(key, FieldTypeKeys.SELECT_LIST, values);
	}

	public void addSeparator() {
		_put(StringPool.BLANK, FieldTypeKeys.SEPARATOR);
	}

	public void addSiteMultiSelect() throws SystemException {
		List<Group> groups =
			GroupLocalServiceUtil.getGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(groups.size());

		for (Group group : groups) {
			if (group.isCommunity() && !group.isControlPanel()) {
				values.add(
					new KeyValuePair(
						group.getName(), String.valueOf(group.getGroupId())));
			}
		}

		addMultiSelectList(FieldTypeKeys.MULTI_SELECT_SITE_LIST, values);
	}

	public void addSubCount(String key) {
		addSubCount(key, false);
	}

	public void addSubCount(String key, boolean required) {
		key = key + StringPool.DASH + FieldKeys.SUB_COUNT;

		if (required) {
			_put(key, FieldTypeKeys.INPUT_REQUIRED);
		}
		else {
			_put(key, FieldTypeKeys.INPUT);
		}
	}

	public void addTime(String key) {
		_put(key, FieldTypeKeys.TIME);
	}

	public void addToParent(String key) {
		key = key + StringPool.DASH;

		List<KeyValuePair> kvpList = new ArrayList<KeyValuePair>();

		kvpList.add(
			new KeyValuePair(
				key + FieldKeys.ADD_TO_ALL_PARENT,
				FieldKeys.ADD_TO_ALL_PARENT));

		kvpList.add(
			new KeyValuePair(
				key + FieldKeys.ADD_TO_INNERMOST_PARENT,
				FieldKeys.ADD_TO_INNERMOST_PARENT));

		kvpList.add(
			new KeyValuePair(
				key + FieldKeys.ADD_TO_RANDOM_PARENT,
				FieldKeys.ADD_TO_RANDOM_PARENT));

		_put(key + FieldKeys.ADD_TO_PARENT, FieldTypeKeys.SELECT_LIST, kvpList);
	}

	public void addUpdateLevel(String key) {
		addUpdateLevel(key, false);
	}

	public void addUpdateLevel(String key, boolean required) {
		key = key + StringPool.DASH + FieldKeys.UPDATE_LEVEL;

		if (required) {
			_put(key, FieldTypeKeys.INPUT_REQUIRED);
		}
		else {
			_put(key, FieldTypeKeys.INPUT);
		}
	}

	public void addUserGroupMultiSelect() throws SystemException {
		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values =
			new ArrayList<KeyValuePair>(userGroups.size());

		for (UserGroup userGroup : userGroups) {
			values.add(
				new KeyValuePair(
					userGroup.getName(),
					String.valueOf(userGroup.getUserGroupId())));
		}

		addMultiSelectList(FieldTypeKeys.MULTI_SELECT_USERGROUP_LIST, values);
	}

	public void addUserMultiSelect() throws SystemException {
		List<User> users = UserLocalServiceUtil.getUsers(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(users.size());

		for (User user : users) {
			values.add(
				new KeyValuePair(
					user.getFullName(), String.valueOf(user.getUserId())));
		}

		addMultiSelectList(FieldTypeKeys.MULTI_SELECT_USER_LIST, values);
	}

	public List<Field> getDisplayFields() {
		return _displayFields;
	}

	public Field getNextField() {
		_index++;

		if (_index < _displayFields.size()) {
			return _displayFields.get(_index);
		}

		return null;
	}

	public boolean hasNextField() {
		return _index < _displayFields.size();
	}

	private void _put(String key, String type) {
		_put(key, type, StringPool.BLANK);
	}

	private void _put(String key, String type, Object values) {
		_displayFields.add(new Field(key, type, values));
	}

	private List<Field> _displayFields;
	private int _index;

}