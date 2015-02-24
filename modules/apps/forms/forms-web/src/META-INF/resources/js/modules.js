;(function() {
	AUI().applyConfig(
		{
			groups: {
				forms: {
					base: '/o/comliferayformsweb/js/',
					modules: {
						'liferay-forms-layout': {
							path: 'form_layout.js',
							requires: [
								'aui-form-builder-page-break-row',
								'aui-form-builder-field-text',
								'aui-layout',
								'liferay-ddm-form'
							]
						},
						'liferay-forms-portlet': {
							path: 'form_portlet.js',
							requires: [
								'aui-base',
								'liferay-forms-steps',
								'liferay-portlet-base'
							]
						},
						'liferay-forms-steps': {
							path: 'form_steps.js',
							requires: [
								'aui-base',
								'aui-tabview',
								'liferay-portlet-base'
							]
						}
					},
					root: '/o/comliferayformsweb/js/'
				}
			}
		}
	);
})();