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

package com.liferay.tool.datamanipulator.entryreader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(name = "parameter")
public class EntryParameterReader {

	public String getName() {
		return _name;
	}

	public String getType() throws ClassNotFoundException {
		String type = _type.replace("&lt;", "<");
		type = type.replace("&gt;", ">");

		return type;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this._name = name;
	}

	@XmlAttribute(name = "type")
	public void setType(String type) {
		type = type.replace("<", "&lt;");
		type = type.replace(">", "&gt;");

		this._type = type;
	}

	private String _name;

	private String _type;

}