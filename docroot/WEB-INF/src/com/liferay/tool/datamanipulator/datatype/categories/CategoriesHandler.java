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

package com.liferay.tool.datamanipulator.datatype.categories;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.util.EntryReaderUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.handler.portlethandler.AbstractPortletHandler;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public class CategoriesHandler extends AbstractPortletHandler implements
		PortletHandlerModel {

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startErase(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public void startErase(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startGenerate(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public void startGenerate(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		// Asset Category

		int categoryCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY);

		int categoryUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY);

		int categoryDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY);

		int categorySubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY);

		EntryTypeReader categoryEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY);

		BaseEntry categoryEntry = new BaseEntry(categoryEntryType);

		EntryHandlerModel categoryHandler = new AssetCategoryHandler(
			categoryCount, categoryUpdate, categoryDepth, categorySubCount,
			categoryEntry, null, requestProcessor);

		long parentVocabularyId = requestProcessor.getLong(
			CategoriesDisplayFields.ASSET_VOCABULARY_ID);

		if (parentVocabularyId == 0) {
			parentVocabularyId = requestProcessor.getLong(
				CategoriesDisplayFields.ASSET_VOCABULARIES_LIST);
		}

		AssetVocabulary parentVocabulary =
			AssetVocabularyLocalServiceUtil.getAssetVocabulary(
				parentVocabularyId);

		if (Validator.isNotNull(parentVocabulary)) {
			categoryHandler.generateEntries(parentVocabularyId);
		}
		else {

			// Asset Vocabulary

			int vocabularyCount = requestProcessor.getCount(
				EntryTypeKeys.GENERAL_ASSET_VOCABULARY);

			int vocabularyUpdate = requestProcessor.getUpdateLevel(
				EntryTypeKeys.GENERAL_ASSET_VOCABULARY);

			EntryTypeReader vocabularyEntryType = EntryReaderUtil.getEntryType(
				EntryTypeKeys.GENERAL_ASSET_VOCABULARY);

			BaseEntry vocabularyEntry = new BaseEntry(vocabularyEntryType);

			EntryHandlerModel vocabularyHandler = new AssetVocabularyHandler(
				vocabularyCount, vocabularyUpdate, 0, 0, vocabularyEntry,
				categoryHandler, requestProcessor);

			vocabularyHandler.generateEntries((long)0);
		}
	}

}