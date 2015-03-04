<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.json.JSONFactoryImpl"%>
<%@page import="com.liferay.portal.kernel.io.unsync.UnsyncStringWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="com.liferay.portal.kernel.template.Template"%>
<%@page import="com.liferay.portal.kernel.template.TemplateManagerUtil"%>
<%@page import="com.liferay.portlet.dynamicdatamapping.model.DDMFormLayoutColumn"%>
<%@page import="com.liferay.portlet.dynamicdatamapping.model.DDMFormLayoutRow"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URL"%>
<%@page import="com.liferay.portal.kernel.template.URLTemplateResource"%>
<%@ include file="init.jsp" %>
<%
URL soyURL = formsRequestHelper.getSoyURL();

URLTemplateResource templateResource = new URLTemplateResource(soyURL.getPath(), soyURL);

Template template = TemplateManagerUtil.getTemplate("soy", templateResource, false);

template.put(TemplateConstants.NAMESPACE, "ddm.layout");

DDMFormLayout ddmLayout = new DDMFormLayout();

DDMFormLayoutRow ddmFormLayoutRow = new DDMFormLayoutRow();
ddmFormLayoutRow.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-1", 4));
ddmFormLayoutRow.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-2", 4));
ddmFormLayoutRow.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-3", 4));
ddmLayout.addDDMFormLayoutRow(ddmFormLayoutRow);


DDMFormLayoutRow ddmFormLayoutRow2 = new DDMFormLayoutRow();
ddmFormLayoutRow2.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-4", 3));
ddmFormLayoutRow2.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-5", 3));
ddmFormLayoutRow2.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-6", 6));
ddmLayout.addDDMFormLayoutRow(ddmFormLayoutRow2);

DDMFormLayoutRow ddmFormLayoutRow3 = new DDMFormLayoutRow();
ddmFormLayoutRow3.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-7", 6));
ddmFormLayoutRow3.addDDMFormLayoutColumn(new DDMFormLayoutColumn("name-8", 6));
ddmLayout.addDDMFormLayoutRow(ddmFormLayoutRow3);

String ddmLayoutSerialized = formsRequestHelper.formSerializer(ddmLayout);

HashMap<String, Object> deserialized = JSONFactoryUtil.looseDeserialize(ddmLayoutSerialized, HashMap.class);

template.put("rows", deserialized.get("rows"));

Writer templateWriter = new UnsyncStringWriter();

template.processTemplate(templateWriter);

%>
<%= templateWriter.toString() %>
