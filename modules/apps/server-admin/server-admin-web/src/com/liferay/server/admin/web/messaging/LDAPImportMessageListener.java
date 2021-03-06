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

package com.liferay.server.admin.web.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.security.exportimport.UserImporterUtil;
import com.liferay.portal.util.PropsValues;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 * @author Philip Jones
 */
@Component(immediate = true, service = MessageListener.class)
public class LDAPImportMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		long time =
			System.currentTimeMillis() - UserImporterUtil.getLastImportTime();

		time = Math.round(time / 60000.0);

		if (time >= PropsValues.LDAP_IMPORT_INTERVAL) {
			UserImporterUtil.importUsers();
		}
	}

}