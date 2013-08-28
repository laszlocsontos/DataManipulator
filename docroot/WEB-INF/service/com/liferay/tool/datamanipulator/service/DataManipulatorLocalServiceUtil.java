/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.tool.datamanipulator.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the data manipulator local service. This utility wraps {@link com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorLocalService
 * @see com.liferay.tool.datamanipulator.service.base.DataManipulatorLocalServiceBaseImpl
 * @see com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl
 * @generated
 */
public class DataManipulatorLocalServiceUtil {
	/**
	* Adds the data manipulator to the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to add
	* @return the data manipulator that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDataManipulator(dataManipulator);
	}

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator createDataManipulator(
		long id) {
		return getService().createDataManipulator(id);
	}

	/**
	* Deletes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator to delete
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDataManipulator(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDataManipulator(id);
	}

	/**
	* Deletes the data manipulator from the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDataManipulator(dataManipulator);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the data manipulator with the primary key.
	*
	* @param id the primary key of the data manipulator to get
	* @return the data manipulator
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator getDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulator(id);
	}

	/**
	* Gets a range of all the data manipulators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of data manipulators to return
	* @param end the upper bound of the range of data manipulators to return (not inclusive)
	* @return the range of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulators(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulators(start, end);
	}

	/**
	* Gets the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int getDataManipulatorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsCount();
	}

	/**
	* Updates the data manipulator in the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to update
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDataManipulator(dataManipulator);
	}

	/**
	* Updates the data manipulator in the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to update
	* @param merge whether to merge the data manipulator with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDataManipulator(dataManipulator, merge);
	}

	public static com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		long groupId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDataManipulator(groupId, className, classPK);
	}

	public static java.util.List<?> getDataManipulatorClassNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorClassNames();
	}

	public static int getDataManipulatorCountByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByClassName(className);
	}

	public static int getDataManipulatorCountByG_C(long groupId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByG_C(groupId, className);
	}

	public static int getDataManipulatorCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByGroupId(groupId);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByClassName(className);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByG_C(groupId, className);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByGroupId(groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static DataManipulatorLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					DataManipulatorLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					portletClassLoader);

			_service = new DataManipulatorLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(DataManipulatorLocalService service) {
		_service = service;
	}

	private static DataManipulatorLocalService _service;
}