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

package com.liferay.portal.template;

import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;

import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public abstract class HybridTemplate extends AbstractTemplate {

	public HybridTemplate(
			TemplateResource templateResource,
			TemplateResource errorTemplateResource, Map<String, Object> context,
			TemplateContextHelper templateContextHelper, String templateManagerName,
			long interval) {
		
		super(
			templateResource, errorTemplateResource, context,
			templateContextHelper, templateManagerName, interval);
	}

	public abstract JavaScriptTemplate getJavaScriptTemplate()
		throws TemplateException;

}