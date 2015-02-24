<%--
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
--%>

<%@ include file="../init.jsp" %>

<%
DDMStructure structure = (DDMStructure)request.getAttribute(WebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE);

String script = ParamUtil.getString(request, "script");

String[] availableLanguageIds = LocalizationUtil.getAvailableLanguageIds(script);

Locale[] availableLocales = new Locale[availableLanguageIds.length];

for (int i = 0; i < availableLanguageIds.length; i++) {
	availableLocales[i] = LocaleUtil.fromLanguageId(availableLanguageIds[i]);
}

JSONArray fieldsJSONArray = null;

DDMFormLayout ddmFormLayout = null;

if (Validator.isNotNull(script)) {
	if (structure != null) {
		try {
			fieldsJSONArray = DDMXSDUtil.getJSONArray(structure, script);
		}
		catch (Exception e) {
			fieldsJSONArray = DDMXSDUtil.getJSONArray(structure.getDefinition());
		}

		ddmFormLayout = structure.getDDMFormLayout();
	}
	else {
		try {
			fieldsJSONArray = DDMXSDUtil.getJSONArray(script);
		}
		catch (Exception e) {
		}

		ddmFormLayout = new DDMFormLayout();
	}
}
else {
	ddmFormLayout = new DDMFormLayout();
}
%>

<div class="separator"><!-- --></div>

<div class="alert alert-danger hide lfr-message-response" id="<portlet:namespace />messageContainer"></div>

<div id="formBuilder"></div>

<%
JSONArray availableLocalesJSONArray = JSONFactoryUtil.createJSONArray();

for (int i = 0; i < availableLocales.length; i++) {
	availableLocalesJSONArray.put(LanguageUtil.getLanguageId(availableLocales[i]));
}

JSONObject localesMapJSONObject = JSONFactoryUtil.createJSONObject();

for (Locale availableLocale : LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId())) {
	localesMapJSONObject.put(LocaleUtil.toLanguageId(availableLocale), availableLocale.getDisplayName(locale));
}
%>

<aui:script use="aui-form-builder,aui-form-builder-field-choice,aui-form-builder-field-date,aui-form-builder-field-grid,aui-form-builder-field-text,aui-form-builder-field-time,aui-form-builder-field-scale,aui-form-builder-field-sentence,aui-form-builder-page-break-row,liferay-forms-layout">
Liferay.Forms.Types = <%= DDMFormFieldTypesHelper.getFieldTypesJSONArray() %>;

console.log(Liferay.Forms.Types);

var layout = new Liferay.Forms.Layout(<%= DDMFormLayoutJSONSerializerUtil.serialize(ddmFormLayout) %>);

new A.FormBuilder(
	{
		fieldTypes: Liferay.Forms.Types,
		//layout: layout
	}
).render('#formBuilder');

console.log(layout.serialize());
</aui:script>