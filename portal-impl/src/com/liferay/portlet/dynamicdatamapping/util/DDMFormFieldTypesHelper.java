/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldType;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldTypeRegistryUtil;

import java.util.Set;

/**
 * @author Bruno Basto
 */
@DoPrivileged
public class DDMFormFieldTypesHelper {

	public static JSONArray getFieldTypesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Set<String> ddmFormFieldTypeNames =
			DDMFormFieldTypeRegistryUtil.getDDMFormFieldTypeNames();

		for (String ddmFormFieldTypeName : ddmFormFieldTypeNames) {
			JSONObject ddmFormFieldTypeJSONObject =
				JSONFactoryUtil.createJSONObject();

			DDMFormFieldType ddmFormFieldType =
				DDMFormFieldTypeRegistryUtil.getDDMFormFieldType(
					ddmFormFieldTypeName);

			ddmFormFieldTypeJSONObject.put(
				"fieldClass", ddmFormFieldType.getFieldJavaScript());
			ddmFormFieldTypeJSONObject.put("icon", ddmFormFieldType.getIcon());
			ddmFormFieldTypeJSONObject.put(
				"label", ddmFormFieldType.getLabel());
			ddmFormFieldTypeJSONObject.put("name", ddmFormFieldTypeName);

			jsonArray.put(ddmFormFieldTypeJSONObject);
		}

		return jsonArray;
	}

}