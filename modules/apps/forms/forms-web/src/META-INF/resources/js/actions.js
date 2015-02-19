Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />delete', '#<portlet:namespace /><%= searchContainerReference.getId() %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

function <portlet:namespace />copyStructure(uri) {
	Liferay.Util.openWindow(
		{
			id: '<portlet:namespace />copyStructure',
			refreshWindow: window,
			title: '<%= UnicodeLanguageUtil.format(request, "copy-x", ddmDisplay.getStructureName(locale), false) %>',
			uri: uri
		}
	);
}

function <portlet:namespace />deleteStructures() {
	if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.attr('method', 'post');
		form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
		form.fm('deleteStructureIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

		submitForm(form, '<portlet:actionURL><portlet:param name="struts_action" value="/dynamic_data_mapping/edit_structure" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>');
	}
}