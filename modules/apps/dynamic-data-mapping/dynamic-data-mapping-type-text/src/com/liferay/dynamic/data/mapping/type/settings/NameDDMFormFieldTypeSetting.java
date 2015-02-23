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

package com.liferay.dynamic.data.mapping.type.settings;

import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldTypeSetting;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldTypeSettingEditor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class NameDDMFormFieldTypeSetting implements DDMFormFieldTypeSetting {

	@Override
	public DDMFormFieldTypeSettingEditor getDDMFormFieldTypeSettingEditor() {
		return new DDMFormFieldTypeSettingEditor() {

			@Override
			public String getEditorType() {
				return "Text";
			}

			@Override
			public Map<String, String> getOptions() {
				Map<String, String> options = new LinkedHashMap<>();

				options.put("required", Boolean.TRUE.toString());

				return options;
			}

		};
	}

	@Override
	public String getName() {
		return "name";
	}

}