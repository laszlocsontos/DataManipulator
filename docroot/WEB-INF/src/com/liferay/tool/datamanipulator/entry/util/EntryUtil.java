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

package com.liferay.tool.datamanipulator.entry.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Tibor Kovács
 *
 */
public class EntryUtil {

	public static String getEditString(String text, String post) {
		text = text.replaceAll(" Edit.*", StringPool.BLANK);
		text += " Edit-" + post;

		return text;
	}

	@SuppressWarnings("deprecation")
	public static ServiceContext getNewServiceContext(
		long groupId, long userId) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(false);
		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);
		serviceContext.setDeriveDefaultPermissions(true);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setIndexingEnabled(false);
		serviceContext.setUserId(userId);

		return serviceContext;
	}

	public static Calendar getRandomCalendar(Calendar cal1, Calendar cal2) {
		if (Validator.isNull(cal1) || Validator.isNull(cal2)) {
			throw new IllegalArgumentException("The calendars must be valid!");
		}

		long fromMilisec = cal1.getTimeInMillis();

		long toMilisec = cal2.getTimeInMillis();

		if (fromMilisec == toMilisec) {
			return (Calendar)cal1.clone();
		}

		if (fromMilisec > toMilisec) {
			long tmp = fromMilisec;

			fromMilisec = toMilisec;

			toMilisec = tmp;
		}

		Calendar randomCalendar = Calendar.getInstance();

		randomCalendar.setTimeInMillis(
			nextLong((toMilisec - fromMilisec)) + fromMilisec);

		return randomCalendar;
	}

	public static boolean nextBoolean() {
		if (_rnd == null) {
			_rnd = new Random();
		}

		return _rnd.nextBoolean();
	}

	public static int nextInt() {
		if (Validator.isNull(_rnd)) {
			_rnd = new Random();
		}

		return _rnd.nextInt();
	}

	public static int nextInt(int max) {
		if (Validator.isNull(_rnd)) {
			_rnd = new Random();
		}

		if (max <= 0) {
			return 0;
		}

		return _rnd.nextInt(max);
	}

	public static int nextInt(int min, int max) {
		if (Validator.isNull(_rnd)) {
			_rnd = new Random();
		}

		return _rnd.nextInt(max - min) + min;
	}

	public static long nextLong(long n) {
		if (Validator.isNull(_rnd)) {
			_rnd = new Random();
		}

		if (n <= 0) {
			throw new IllegalArgumentException("n must be positive");
		}

		long bits, val;

		do {
			bits = _rnd.nextInt();
			val = bits % n;
		} while (bits - val + (n-1) < 0);

		return val;
	}

	private static Random _rnd;

}