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

import com.liferay.portal.kernel.util.KeyValuePair;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class Field {
	public Field() {
		this(null, null, null);
	}

	public Field(String key, String type, Object values) {
		set(key, type, values);
	}

	public String getKey() {
		return _key;
	}

	public String getValue() {
		return (String)_values;
	}

	@SuppressWarnings("unchecked")
	public List<KeyValuePair> getValues() {
		return (List<KeyValuePair>)_values;
	}

	public boolean isTypeOf(String type) {
		return _type.equals(type);
	}

	public void set(String key, String type, Object values) {
		setKey(key);
		setType(type);
		setValues(values);
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setValues(Object values) {
		_values = values;
	}

	private String _key;
	private String _type;
	private Object _values;

}