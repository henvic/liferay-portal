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

package com.liferay.portlet.softwarecatalog.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.model.SCLicenseModel;
import com.liferay.portlet.softwarecatalog.model.SCLicenseSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the SCLicense service. Represents a row in the &quot;SCLicense&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SCLicenseModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SCLicenseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCLicenseImpl
 * @see SCLicense
 * @see SCLicenseModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SCLicenseModelImpl extends BaseModelImpl<SCLicense>
	implements SCLicenseModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a s c license model instance should use the {@link SCLicense} interface instead.
	 */
	public static final String TABLE_NAME = "SCLicense";
	public static final Object[][] TABLE_COLUMNS = {
			{ "licenseId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "openSource", Types.BOOLEAN },
			{ "active_", Types.BOOLEAN },
			{ "recommended", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("licenseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("openSource", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("recommended", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table SCLicense (licenseId LONG not null primary key,companyId LONG,name VARCHAR(75) null,url STRING null,openSource BOOLEAN,active_ BOOLEAN,recommended BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table SCLicense";
	public static final String ORDER_BY_JPQL = " ORDER BY scLicense.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SCLicense.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.softwarecatalog.model.SCLicense"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.softwarecatalog.model.SCLicense"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.softwarecatalog.model.SCLicense"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long RECOMMENDED_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SCLicense toModel(SCLicenseSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SCLicense model = new SCLicenseImpl();

		model.setLicenseId(soapModel.getLicenseId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setOpenSource(soapModel.getOpenSource());
		model.setActive(soapModel.getActive());
		model.setRecommended(soapModel.getRecommended());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SCLicense> toModels(SCLicenseSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SCLicense> models = new ArrayList<SCLicense>(soapModels.length);

		for (SCLicenseSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_SCLICENSES_SCPRODUCTENTRIES_NAME = "SCLicenses_SCProductEntries";
	public static final Object[][] MAPPING_TABLE_SCLICENSES_SCPRODUCTENTRIES_COLUMNS =
		{
			{ "companyId", Types.BIGINT },
			{ "licenseId", Types.BIGINT },
			{ "productEntryId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_SCLICENSES_SCPRODUCTENTRIES_SQL_CREATE =
		"create table SCLicenses_SCProductEntries (companyId LONG not null,licenseId LONG not null,productEntryId LONG not null,primary key (companyId, licenseId, productEntryId))";
	public static final boolean FINDER_CACHE_ENABLED_SCLICENSES_SCPRODUCTENTRIES =
		GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.SCLicenses_SCProductEntries"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.softwarecatalog.model.SCLicense"));

	public SCLicenseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _licenseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLicenseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SCLicense.class;
	}

	@Override
	public String getModelClassName() {
		return SCLicense.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseId", getLicenseId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("openSource", getOpenSource());
		attributes.put("active", getActive());
		attributes.put("recommended", getRecommended());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseId = (Long)attributes.get("licenseId");

		if (licenseId != null) {
			setLicenseId(licenseId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Boolean openSource = (Boolean)attributes.get("openSource");

		if (openSource != null) {
			setOpenSource(openSource);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Boolean recommended = (Boolean)attributes.get("recommended");

		if (recommended != null) {
			setRecommended(recommended);
		}
	}

	@JSON
	@Override
	public long getLicenseId() {
		return _licenseId;
	}

	@Override
	public void setLicenseId(long licenseId) {
		_licenseId = licenseId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@JSON
	@Override
	public boolean getOpenSource() {
		return _openSource;
	}

	@Override
	public boolean isOpenSource() {
		return _openSource;
	}

	@Override
	public void setOpenSource(boolean openSource) {
		_openSource = openSource;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public boolean getRecommended() {
		return _recommended;
	}

	@Override
	public boolean isRecommended() {
		return _recommended;
	}

	@Override
	public void setRecommended(boolean recommended) {
		_columnBitmask |= RECOMMENDED_COLUMN_BITMASK;

		if (!_setOriginalRecommended) {
			_setOriginalRecommended = true;

			_originalRecommended = _recommended;
		}

		_recommended = recommended;
	}

	public boolean getOriginalRecommended() {
		return _originalRecommended;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SCLicense.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SCLicense toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SCLicense)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SCLicenseImpl scLicenseImpl = new SCLicenseImpl();

		scLicenseImpl.setLicenseId(getLicenseId());
		scLicenseImpl.setCompanyId(getCompanyId());
		scLicenseImpl.setName(getName());
		scLicenseImpl.setUrl(getUrl());
		scLicenseImpl.setOpenSource(getOpenSource());
		scLicenseImpl.setActive(getActive());
		scLicenseImpl.setRecommended(getRecommended());

		scLicenseImpl.resetOriginalValues();

		return scLicenseImpl;
	}

	@Override
	public int compareTo(SCLicense scLicense) {
		int value = 0;

		value = getName().compareTo(scLicense.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SCLicense)) {
			return false;
		}

		SCLicense scLicense = (SCLicense)obj;

		long primaryKey = scLicense.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SCLicenseModelImpl scLicenseModelImpl = this;

		scLicenseModelImpl._originalActive = scLicenseModelImpl._active;

		scLicenseModelImpl._setOriginalActive = false;

		scLicenseModelImpl._originalRecommended = scLicenseModelImpl._recommended;

		scLicenseModelImpl._setOriginalRecommended = false;

		scLicenseModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SCLicense> toCacheModel() {
		SCLicenseCacheModel scLicenseCacheModel = new SCLicenseCacheModel();

		scLicenseCacheModel.licenseId = getLicenseId();

		scLicenseCacheModel.companyId = getCompanyId();

		scLicenseCacheModel.name = getName();

		String name = scLicenseCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			scLicenseCacheModel.name = null;
		}

		scLicenseCacheModel.url = getUrl();

		String url = scLicenseCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			scLicenseCacheModel.url = null;
		}

		scLicenseCacheModel.openSource = getOpenSource();

		scLicenseCacheModel.active = getActive();

		scLicenseCacheModel.recommended = getRecommended();

		return scLicenseCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{licenseId=");
		sb.append(getLicenseId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", openSource=");
		sb.append(getOpenSource());
		sb.append(", active=");
		sb.append(getActive());
		sb.append(", recommended=");
		sb.append(getRecommended());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.softwarecatalog.model.SCLicense");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>licenseId</column-name><column-value><![CDATA[");
		sb.append(getLicenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>openSource</column-name><column-value><![CDATA[");
		sb.append(getOpenSource());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recommended</column-name><column-value><![CDATA[");
		sb.append(getRecommended());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SCLicense.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SCLicense.class
		};
	private long _licenseId;
	private long _companyId;
	private String _name;
	private String _url;
	private boolean _openSource;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private boolean _recommended;
	private boolean _originalRecommended;
	private boolean _setOriginalRecommended;
	private long _columnBitmask;
	private SCLicense _escapedModel;
}