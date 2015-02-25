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
%>

<div class="separator"><!-- --></div>

<div id="formBuilder"></div>

<aui:script use="liferay-forms-form-builder,liferay-forms-layout">
var fieldTypes = Liferay.Forms.FormBuilder.Util.getFieldTypes(<%= DDMFormFieldTypesHelper.getFieldTypesJSONArray() %>);
var layout = new Liferay.Forms.Layout(<%= DDMFormLayoutJSONSerializerUtil.serialize(ddmFormLayout) %>);

new Liferay.Forms.FormBuilder(
	{
		fieldTypes: fieldTypes,
		//layout: layout
	}
).render('#formBuilder');
</aui:script>