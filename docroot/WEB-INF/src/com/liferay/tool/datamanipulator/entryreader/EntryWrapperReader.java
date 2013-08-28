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

import com.liferay.tool.datamanipulator.entry.NoSuchEntryException;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(namespace = "com.liferay.tool.datamanipulator.entryreader")
public class EntryWrapperReader {

	public EntryTypeReader getEntry(String entryTypeKey)
		throws NoSuchEntryException {

		for (EntryTypeReader entry : this._entryList) {
			if (entryTypeKey.equals(entry.getName())) {
				return entry;
			}
		}

		throw new NoSuchEntryException(
			"The requested '" + entryTypeKey + "' entry not found.");
	}

	public ArrayList<EntryTypeReader> getEntryList() {
		return this._entryList;
	}

	@XmlElement(name = "entry")
	public void setEntryList(ArrayList<EntryTypeReader> entryList) {
		this._entryList = entryList;
	}

	private ArrayList<EntryTypeReader> _entryList;

}