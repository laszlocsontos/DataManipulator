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
public class WikiHandler extends AbstractPortletHandler implements
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

		// Wiki Page

		int pageCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		EntryTypeReader pageEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		BaseEntry pageEntry = new BaseEntry(pageEntryType);

		EntryHandlerModel pageHandler = new WikiPageHandler(
			pageCount, pageUpdate, pageDepth, pageSubCount, pageEntry,
			null, requestProcessor);

		// Wiki Node

		int nodeCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_WIKI_NODE);

		int nodeUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_WIKI_NODE);

		EntryTypeReader nodeEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_WIKI_NODE);

		BaseEntry nodeEntry = new BaseEntry(nodeEntryType);

		EntryHandlerModel nodeHandler = new WikiNodeHandler(
			nodeCount, nodeUpdate, 0, 0, nodeEntry, pageHandler,
			requestProcessor);

		nodeHandler.generateEntries((long)0);
	}

}