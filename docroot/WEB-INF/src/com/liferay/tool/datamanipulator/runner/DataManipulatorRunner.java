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

package com.liferay.tool.datamanipulator.runner;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public class DataManipulatorRunner extends Thread {
	public DataManipulatorRunner(
		PortletHandlerModel handler, RequestProcessor requestProcessor) {

		_handler = handler;
		_requestProcessor = requestProcessor;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();

		_log.error(
			(Thread.currentThread()).getName() + " start add entries at " +
			String.valueOf(startTime) + StringPool.PERIOD);

		try {
			_handler.startGenerate(_requestProcessor);
		}
		catch (PortalException e) {
			_log.error(e, e);
		}
		catch (SystemException e) {
			_log.error(e, e);
		}

		long endTime = System.currentTimeMillis();

		_log.error(
			(Thread.currentThread()).getName() + " finish add entries at " +
			String.valueOf(endTime)+ StringPool.PERIOD);

		_log.error(
			"The whole process takes " +
			String.valueOf(endTime - startTime) + "ms.");
	}

	private static Log _log = LogFactoryUtil.getLog(
		DataManipulatorRunner.class);

	private PortletHandlerModel _handler;
	private RequestProcessor _requestProcessor;

}