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

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(name = "entry")
public class EntryTypeReader {
	public EntryMethodReader getMethod(String methodType)
		throws NoSuchMethodException {

		for (EntryMethodReader method : this._methods) {
			if (methodType.equals(method.getMethodType())) {
				return method;
			}
		}

		throw new NoSuchMethodException(
			"The requested '" + methodType + "' not exits.");
	}

	public ArrayList<EntryMethodReader> getMethods() {
		return this._methods;
	}

	public String getName() {
		return _name;
	}

	@XmlElement(name = "method")
	public void setMethods(ArrayList<EntryMethodReader> methods) {
		this._methods = methods;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this._name = name;
	}

	private ArrayList<EntryMethodReader> _methods;

	private String _name;

}