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

package com.liferay.tool.datamanipulator.entry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.tool.datamanipulator.entryreader.EntryMethodReader;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Tibor Kovács
 *
 */
public class BaseEntry {

	public BaseEntry(EntryTypeReader entryType) {
		_entryType = entryType;
	}

	public Object createEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		return _runInvoke(_getAddMethod(), entryArgs);
	}

	public void deleteEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		_runInvoke(_getDeleteMethod(), entryArgs);
	}

	public String getEntryName() {
		return _entryType.getName();
	}

	public void updateEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		_runInvoke(_getUpdateMethod(), entryArgs);
	}

	private EntryMethodReader _getAddMethod() throws NoSuchMethodException {
		return _entryType.getMethod("add");
	}

	private EntryMethodReader _getDeleteMethod() throws NoSuchMethodException {
		return _entryType.getMethod("delete");
	}

	private EntryMethodReader _getUpdateMethod() throws NoSuchMethodException {
		return _entryType.getMethod("update");
	}

	private Object _runInvoke(
			EntryMethodReader entryMethod, EntryArgs entryArgs)
		throws PortalException, SystemException {

		try {
			Class<?> clazz = Class.forName(entryMethod.getClazz());

			Method method = clazz.getMethod(
				entryMethod.getMethodName(),
				entryMethod.getParameterListClazzs());

			Object[] args = entryArgs.getArgs(
				entryMethod.getParameterListNames());

			return method.invoke(clazz, args);
		}
		catch (SecurityException e) {
			_log.error(e, e);
		}
		catch (NoSuchMethodException e) {
			_log.error(e, e);
		}
		catch (IllegalArgumentException e) {
			_log.error(e, e);
		}
		catch (IllegalAccessException e) {
			_log.error(e, e);
		}
		catch (ClassNotFoundException e) {
			_log.error(e, e);
		}
		catch (InvocationTargetException e) {
			if (e.getCause() instanceof SystemException) {
				throw (SystemException)e.getCause();
			}
			else if (e.getCause() instanceof PortalException) {
				throw (PortalException)e.getCause();
			}

			_log.error(e.getCause(), e.getCause());
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseEntry.class);

	private EntryTypeReader _entryType;

}