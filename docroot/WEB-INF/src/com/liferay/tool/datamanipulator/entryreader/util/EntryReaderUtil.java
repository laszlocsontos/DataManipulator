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

package com.liferay.tool.datamanipulator.entryreader.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.tool.datamanipulator.entry.NoSuchEntryException;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.EntryWrapperReader;

import java.io.File;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Tibor Kovács
 *
 */
public class EntryReaderUtil {

	public static EntryTypeReader getEntryType(String entryTypeKey) {
		ClassLoader classLoader = EntryReaderUtil.class.getClassLoader();

		URL entryWrapperFileURL = classLoader.getResource("EntryTypes.xml");

		File entryWrapperFile = new File(entryWrapperFileURL.getPath());

		EntryWrapperReader entryWrapper = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
				EntryWrapperReader.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			entryWrapper = (EntryWrapperReader)jaxbUnmarshaller.unmarshal(
				entryWrapperFile);
		}
		catch (JAXBException e) {
			_log.error(e, e);
		}

		EntryTypeReader entryType = null;

		try {
			entryType = entryWrapper.getEntry(entryTypeKey);
		}
		catch (NoSuchEntryException e) {
			_log.error(e, e);
		}

		return entryType;
	}

	private static Log _log = LogFactoryUtil.getLog(EntryReaderUtil.class);

}