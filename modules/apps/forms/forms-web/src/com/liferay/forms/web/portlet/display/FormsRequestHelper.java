package com.liferay.forms.web.portlet.display;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.display.context.util.BaseRequestHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.search.StructureSearchTerms;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;
import com.liferay.portlet.dynamicdatamapping.util.DDMDisplay;
import com.liferay.portlet.dynamicdatamapping.util.DDMDisplayRegistryUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMUtil;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
public class FormsRequestHelper extends BaseRequestHelper {

	public FormsRequestHelper(HttpServletRequest request) {
		super(request);
	}

	public DDMDisplay getDDMDisplay() {
		String refererPortletName = ParamUtil.getString(
			getRequest(), "refererPortletName", getPortletName());

		return DDMDisplayRegistryUtil.getDDMDisplay(refererPortletName);
	}

	public PortletURL getDDMStructureRowURL(
		RenderResponse renderResponse, DDMStructure ddmStructure) {

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter(
			"struts_action", "/dynamic_data_mapping/edit_structure");
		rowURL.setParameter("redirect", PortalUtil.getCurrentURL(getRequest()));
		rowURL.setParameter(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)));
		rowURL.setParameter(
			"classPK", String.valueOf(ddmStructure.getStructureId()));

		return rowURL;
	}

	public String getOrderByCol() {
		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(getRequest());

		String orderByCol = ParamUtil.getString(getRequest(), "orderByCol");

		if (Validator.isNotNull(orderByCol)) {
			portalPreferences.setValue(
				PortletKeys.DYNAMIC_DATA_MAPPING, "entries-order-by-col",
				orderByCol);
		}
		else {
			orderByCol = portalPreferences.getValue(
				PortletKeys.DYNAMIC_DATA_MAPPING, "entries-order-by-col", "id");
		}

		return orderByCol;
	}

	public OrderByComparator<DDMStructure> getOrderByComparator() {
		String orderByType = getOrderByType();
		String orderByCol = getOrderByCol();

		return DDMUtil.getStructureOrderByComparator(orderByCol, orderByType);
	}

	public String getOrderByType() {
		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(getRequest());

		String orderByType = ParamUtil.getString(getRequest(), "orderByType");

		if (Validator.isNotNull(orderByType)) {
			portalPreferences.setValue(
				PortletKeys.DYNAMIC_DATA_MAPPING, "entries-order-by-type",
				orderByType);
		}
		else {
			orderByType = portalPreferences.getValue(
				PortletKeys.DYNAMIC_DATA_MAPPING, "entries-order-by-type",
				"asc");
		}

		return orderByType;
	}

	public long getScopeClassNameId() {
		return PortalUtil.getClassNameId(getDDMDisplay().getStructureType());
	}

	public String getStorageTypeValue(String scopeStorageType) {
		String storageTypeValue = StringPool.BLANK;

		if (scopeStorageType.equals("expando")) {
			storageTypeValue = StorageType.EXPANDO.getValue();
		}
		else if (scopeStorageType.equals("json")) {
			storageTypeValue = StorageType.JSON.getValue();
		}
		else if (scopeStorageType.equals("xml")) {
			storageTypeValue = StorageType.XML.getValue();
		}

		return storageTypeValue;
	}

	public String getTemplateTypeValue(String scopeTemplateType) {
		String templateTypeValue = StringPool.BLANK;

		if (scopeTemplateType.equals(
				DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY)) {

			templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
		}
		else if (scopeTemplateType.equals(
					DDMTemplateConstants.TEMPLATE_TYPE_FORM)) {

			templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_FORM;
		}

		return templateTypeValue;
	}

	public PortletURL getViewPortletURL(
		RenderResponse renderResponse, long groupId, String tabs1) {

		PortletURL viewPortletURL = renderResponse.createRenderURL();

		viewPortletURL.setParameter(
			"struts_action", "/dynamic_data_mapping/view");
		viewPortletURL.setParameter("groupId", String.valueOf(groupId));
		viewPortletURL.setParameter("tabs1", tabs1);

		return viewPortletURL;
	}

	public boolean isShowAncestorScopes() {
		return ParamUtil.getBoolean(getRequest(), "showAncestorScopes");
	}

	public void populateSearchContainer(
			SearchContainer<DDMStructure> searchContainer)
		throws PortalException {

		Company company = getCompany();

		StructureSearchTerms searchTerms =
			(StructureSearchTerms)searchContainer.getSearchTerms();

		long groupId = getScopeGroupId();

		long[] groupIds = new long[] {groupId};

		if (isShowAncestorScopes()) {
			groupIds = PortalUtil.getCurrentAndAncestorSiteGroupIds(groupId);
		}

		List<DDMStructure> results = searchContainer.getResults();

		int total = searchContainer.getTotal();

		if (searchTerms.isAdvancedSearch()) {
			total = DDMStructureServiceUtil.searchCount(
				company.getCompanyId(), groupIds, searchTerms.getClassNameId(),
				searchTerms.getName(), searchTerms.getDescription(),
				searchTerms.getStorageType(),
				DDMStructureConstants.TYPE_DEFAULT,
				searchTerms.isAndOperator());

			searchContainer.setTotal(total);

			results = DDMStructureServiceUtil.search(
				company.getCompanyId(), groupIds, searchTerms.getClassNameId(),
				searchTerms.getName(), searchTerms.getDescription(),
				searchTerms.getStorageType(),
				DDMStructureConstants.TYPE_DEFAULT, searchTerms.isAndOperator(),
				searchContainer.getStart(), searchContainer.getEnd(),
				searchContainer.getOrderByComparator());
		}
		else {
			long scopeClassNameId = getScopeClassNameId();

			total = DDMStructureServiceUtil.searchCount(
				company.getCompanyId(), groupIds, scopeClassNameId,
				searchTerms.getKeywords());

			searchContainer.setTotal(total);

			results = DDMStructureServiceUtil.search(
				company.getCompanyId(), groupIds, scopeClassNameId,
				searchTerms.getKeywords(), searchContainer.getStart(),
				searchContainer.getEnd(),
				searchContainer.getOrderByComparator());
		}

		searchContainer.setResults(results);
	}

}