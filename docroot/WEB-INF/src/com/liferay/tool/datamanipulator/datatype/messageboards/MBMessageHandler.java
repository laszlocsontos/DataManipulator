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

package com.liferay.tool.datamanipulator.datatype.messageboards;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class MBMessageHandler extends AbstractEntryHandler implements
		EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param subEntryHandler
	 * @param requestProcessor
	 */
	public MBMessageHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler,
		RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		long threadId = parentId;

		MBThread parentThread = MBThreadLocalServiceUtil.getThread(threadId);

		String addToTag = requestProcessor.getAddToParent(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		long parentMessageId = 0;

		List<MBMessage> messages =
			MBMessageLocalServiceUtil.getThreadMessages(
				threadId, WorkflowConstants.STATUS_ANY);

		if (addToTag.equals(FieldKeys.ADD_TO_ALL_PARENT)) {
			parentMessageId = messages.get(0).getMessageId();
		}
		else if (addToTag.equals(FieldKeys.ADD_TO_INNERMOST_PARENT)) {
			int messageCount = parentThread.getMessageCount();

			parentMessageId = messages.get(messageCount).getMessageId();
		}
		else if (addToTag.equals(FieldKeys.ADD_TO_RANDOM_PARENT)) {
			int messageCount = parentThread.getMessageCount();

			int rndIndex = EntryUtil.nextInt(messageCount);

			parentMessageId = messages.get(rndIndex).getMessageId();
		}

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("categoryId", parentThread.getCategoryId());
		args.setParameter("threadId", threadId);
		args.setParameter("parentMessageId", parentMessageId);
		args.setParameter(
			"subject", "Test MB Message" + postString + " Subject");

		args.setParameter("body", "Test MB Message" + postString + " Body");
		args.setParameter("priority", 0.0);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */
	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((MBMessage)createdEntry).getGroupId(), MBMessage.class.getName(),
			((MBMessage)createdEntry).getMessageId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */
	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		MBMessage message = MBMessageLocalServiceUtil.getMBMessage(entryId);

		String subject = EntryUtil.getEditString(
			message.getSubject(), postString);

		String body = message.getBody();
		body += "\nTest MB Message " + postString + ". Edit Body";

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("messageId", entryId);
		args.setParameter("subject", subject);
		args.setParameter("body", body);
		args.setParameter("existingFiles", new ArrayList<String>(0));
		args.setParameter("priority", message.getPriority());

		return args;
	}

}