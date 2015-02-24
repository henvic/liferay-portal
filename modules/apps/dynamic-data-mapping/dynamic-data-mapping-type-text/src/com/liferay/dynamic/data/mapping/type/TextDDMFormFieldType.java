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

package com.liferay.dynamic.data.mapping.type;

import com.liferay.dynamic.data.mapping.type.settings.HelpDDMFormFieldTypeSetting;
import com.liferay.dynamic.data.mapping.type.settings.NameDDMFormFieldTypeSetting;
import com.liferay.dynamic.data.mapping.type.settings.RequiredDDMFormFieldTypeSetting;
import com.liferay.dynamic.data.mapping.type.settings.TypeDDMFormFieldTypeSetting;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.template.HybridTemplate;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldRenderer;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldType;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldTypeSetting;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldValueAccessor;
import com.liferay.portlet.dynamicdatamapping.registry.DDMFormFieldValueRendererAccessor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = DDMFormFieldType.class)
public class TextDDMFormFieldType implements DDMFormFieldType {

	@Override
	public List<DDMFormFieldTypeSetting> getAdvancedSettings() {
		return Collections.emptyList();
	}

	@Override
	public List<DDMFormFieldTypeSetting> getBasicSettings() {
		List<DDMFormFieldTypeSetting> basicSettings = new ArrayList<>();

		basicSettings.add(new NameDDMFormFieldTypeSetting());
		basicSettings.add(new HelpDDMFormFieldTypeSetting());
		basicSettings.add(new TypeDDMFormFieldTypeSetting());
		basicSettings.add(new RequiredDDMFormFieldTypeSetting());

		return basicSettings;
	}

	@Override
	public DDMFormFieldRenderer getDDMFormFieldRenderer() {
		return _textDDMFormFieldRenderer;
	}

	@Override
	public DDMFormFieldValueAccessor<String> getDDMFormFieldValueAccessor(
		Locale locale) {

		return new TextDDMFormFieldValueAccessor(locale);
	}

	@Override
	public DDMFormFieldValueRendererAccessor
		getDDMFormFieldValueRendererAccessor(Locale locale) {

		return new TextDDMFormFieldValueRendererAccessor(
			getDDMFormFieldValueAccessor(locale));
	}

	@Override
	public String getName() {
		return "text";
	}

	@Reference(service = TextDDMFormFieldRenderer.class, unbind = "-")
	protected void setDDMFormFieldRenderer(
		TextDDMFormFieldRenderer textDDMFormFieldRenderer) {

		_textDDMFormFieldRenderer = textDDMFormFieldRenderer;
	}

	private TextDDMFormFieldRenderer _textDDMFormFieldRenderer;

	@Override
	public URL getDDMFormFieldJavaScriptRenderer() throws Exception {
		HybridTemplate hybridTemplate = (HybridTemplate)
			TemplateManagerUtil.getTemplate(
				TemplateConstants.LANG_TYPE_SOY,
				_textDDMFormFieldRenderer.getTemplateResource(), false);
		
		File file = new File(_textDDMFormFieldRenderer.getTemplatePath() + ".js");
				
		FileUtil.write(file, hybridTemplate.getJavaScriptTemplate().getJavaScriptSources().get(0));
		
		return file.toURI().toURL();
	}

	@Override
	public String getFieldJavaScript() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}