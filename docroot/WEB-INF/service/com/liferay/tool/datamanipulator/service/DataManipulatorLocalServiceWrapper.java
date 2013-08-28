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

/**
 * <p>
 * This class is a wrapper for {@link DataManipulatorLocalService}.
 * </p>
 *
 * @author    Tibor KovĂˇcs
 * @see       DataManipulatorLocalService
 * @generated
 */
public class DataManipulatorLocalServiceWrapper
	implements DataManipulatorLocalService {
	public DataManipulatorLocalServiceWrapper(
		DataManipulatorLocalService dataManipulatorLocalService) {
		_dataManipulatorLocalService = dataManipulatorLocalService;
	}

	/**
	* Adds the data manipulator to the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to add
	* @return the data manipulator that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.addDataManipulator(dataManipulator);
	}

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator createDataManipulator(
		long id) {
		return _dataManipulatorLocalService.createDataManipulator(id);
	}

	/**
	* Deletes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator to delete
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteDataManipulator(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dataManipulatorLocalService.deleteDataManipulator(id);
	}

	/**
	* Deletes the data manipulator from the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataManipulatorLocalService.deleteDataManipulator(dataManipulator);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the data manipulator with the primary key.
	*
	* @param id the primary key of the data manipulator to get
	* @return the data manipulator
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator getDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulator(id);
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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulators(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulators(start, end);
	}

	/**
	* Gets the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int getDataManipulatorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsCount();
	}

	/**
	* Updates the data manipulator in the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to update
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.updateDataManipulator(dataManipulator);
	}

	/**
	* Updates the data manipulator in the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator to update
	* @param merge whether to merge the data manipulator with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.updateDataManipulator(dataManipulator,
			merge);
	}

	public com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		long groupId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.addDataManipulator(groupId,
			className, classPK);
	}

	public java.util.List<?> getDataManipulatorClassNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorClassNames();
	}

	public int getDataManipulatorCountByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByClassName(className);
	}

	public int getDataManipulatorCountByG_C(long groupId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByG_C(groupId,
			className);
	}

	public int getDataManipulatorCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByGroupId(groupId);
	}

	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByClassName(className);
	}

	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByG_C(groupId,
			className);
	}

	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByGroupId(groupId);
	}

	public DataManipulatorLocalService getWrappedDataManipulatorLocalService() {
		return _dataManipulatorLocalService;
	}

	private DataManipulatorLocalService _dataManipulatorLocalService;
}