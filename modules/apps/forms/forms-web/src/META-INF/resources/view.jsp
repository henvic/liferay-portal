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

<%@ include file="init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "structures");

long groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getSiteGroupId());

PortletURL portletURL = formsRequestHelper.getViewPortletURL(renderResponse, groupId, tabs1);
%>

<liferay-ui:error exception="<%= RequiredStructureException.class %>">

	<%
	RequiredStructureException rse = (RequiredStructureException)errorException;
	%>

	<c:choose>
		<c:when test="<%= rse.getType() == RequiredStructureException.REFERENCED_TEMPLATE %>">
			<liferay-ui:message key="required-structures-could-not-be-deleted.-they-are-referenced-by-templates" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="required-structures-could-not-be-deleted" />
		</c:otherwise>
	</c:choose>
</liferay-ui:error>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="deleteStructureIds" type="hidden" />

	<%
	String orderByCol = formsRequestHelper.getOrderByCol();
	String orderByType = formsRequestHelper.getOrderByType();

	OrderByComparator<DDMStructure> orderByComparator = formsRequestHelper.getOrderByComparator();
	%>

	<liferay-ui:search-container
		orderByCol="<%= orderByCol %>"
		orderByComparator="<%= orderByComparator %>"
		orderByType="<%= orderByType %>"
		rowChecker="<%= new RowChecker(renderResponse) %>"
		searchContainer="<%= new StructureSearch(renderRequest, portletURL) %>"
	>

		<c:if test="<%= showToolbar %>">

			<%
			request.setAttribute(WebKeys.SEARCH_CONTAINER, searchContainer);
			%>

			<liferay-util:include page="toolbar.jsp">
				<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
			</liferay-util:include>
		</c:if>

		<liferay-ui:search-container-results>

			<%
			formsRequestHelper.populateSearchContainer(searchContainer);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.dynamicdatamapping.model.DDMStructure"
			keyProperty="structureId"
			modelVar="structure"
		>

			<%
			PortletURL rowURL = formsRequestHelper.getDDMStructureRowURL(renderResponse, structure);

			String rowHREF = rowURL.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="id"
				orderable="<%= true %>"
				orderableProperty="id"
				property="structureId"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="name"
				value="<%= HtmlUtil.escape(structure.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="description"
				value="<%= HtmlUtil.escape(structure.getDescription(locale)) %>"
			/>

			<c:if test="<%= Validator.isNull(formsRequestHelper.getStorageTypeValue(scopeStorageType)) %>">
				<liferay-ui:search-container-column-text
					href="<%= rowHREF %>"
					name="storage-type"
					value="<%= LanguageUtil.get(request, structure.getStorageType()) %>"
				/>
			</c:if>

			<c:if test="<%= formsRequestHelper.getScopeClassNameId() == 0 %>">
				<liferay-ui:search-container-column-text
					href="<%= rowHREF %>"
					name="type"
					value="<%= ResourceActionsUtil.getModelResource(locale, structure.getClassName()) %>"
				/>
			</c:if>

			<%
			Group group = GroupLocalServiceUtil.getGroup(structure.getGroupId());
			%>

			<liferay-ui:search-container-column-text
				name="scope"
				value="<%= LanguageUtil.get(request, group.getScopeLabel(themeDisplay)) %>"
			/>

			<liferay-ui:search-container-column-date
				href="<%= rowHREF %>"
				name="modified-date"
				orderable="<%= true %>"
				orderableProperty="modified-date"
				value="<%= structure.getModifiedDate() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/html/portlet/dynamic_data_mapping/structure_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<c:if test="<%= total > 0 %>">
			<aui:button-row>
				<aui:button cssClass="delete-structures-button" disabled="<%= true %>" name="delete" onClick='<%= renderResponse.getNamespace() + "deleteStructures();" %>' value="delete" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>