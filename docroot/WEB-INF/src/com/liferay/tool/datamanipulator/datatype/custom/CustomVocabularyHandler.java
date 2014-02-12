/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.tool.datamanipulator.datatype.custom;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.tool.datamanipulator.datatype.categories.AssetVocabularyHandler;
import com.liferay.tool.datamanipulator.datatype.site.SiteEntryHandler;
import com.liferay.tool.datamanipulator.datatype.user.UserEntryHandler;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.util.EntryReaderUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.handler.portlethandler.AbstractPortletHandler;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author lcsontos
 */
public class CustomVocabularyHandler extends AbstractPortletHandler
	implements PortletHandlerModel {

	@Override
	public void startErase(RequestProcessor requestProcessor)
		throws PortalException, SystemException {
	}

	@Override
	public void startGenerate(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		// User

		EntryTypeReader userEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.PORTAL_USER);

		BaseEntry userEntry = new BaseEntry(userEntryType);

		EntryHandlerModel userHandler = new UserEntryHandler(
			1, 0, 0, 0, userEntry, null, requestProcessor) {

			@Override
			public DataManipulator getDataManipulatorFromObject(
				Object createdEntry) throws PortalException, SystemException {

				_user = (User)createdEntry;

				_log.error(
					"*** Created user: " + _user.getEmailAddress() +
						" / password: test");

				_log.error("*** Company ID: " + _user.getCompanyId());

				return super.getDataManipulatorFromObject(createdEntry);
			}

		};

		userHandler.generateEntries((long)0);

		// Asset Vocabulary

		final int vocabularyCount = 1;

		EntryTypeReader vocabularyEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_ASSET_VOCABULARY);

		BaseEntry vocabularyEntry = new BaseEntry(vocabularyEntryType);

		EntryHandlerModel vocabularyHandler = new AssetVocabularyHandler(
			vocabularyCount, 0, 0, 0, vocabularyEntry, null, requestProcessor);

		// Site

		final int siteCount = 1000;

		EntryTypeReader siteEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.PORTAL_SITE);

		BaseEntry siteEntry = new BaseEntry(siteEntryType);

		EntryHandlerModel siteHandler = new SiteEntryHandler(
			siteCount, 0, 0, 0, siteEntry, vocabularyHandler,
			requestProcessor) {

			@Override
			public DataManipulator getDataManipulatorFromObject(
					Object createdEntry)
				throws PortalException, SystemException {

				long groupId = ((Group)createdEntry).getGroupId();

				UserLocalServiceUtil.addGroupUser(groupId, _user.getUserId());

				return super.getDataManipulatorFromObject(createdEntry);
			}

		};

		siteHandler.generateEntries((long)0);
	}

	private static Log _log = LogFactoryUtil.getLog(
		CustomVocabularyHandler.class);

	private User _user;

}