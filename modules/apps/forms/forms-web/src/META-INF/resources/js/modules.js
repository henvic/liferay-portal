;(function() {
	AUI().applyConfig(
		{
			groups: {
				forms: {
					base: '/o/comliferayformsweb/js/',
					modules: {
						'liferay-forms-field-base': {
							path: 'form_field_base.js',
							requires: [
								'aui-form-builder-field-base',
								'aui-form-field'
							]
						},
						'liferay-forms-form-builder': {
							path: 'form_builder.js',
							requires: [
								'aui-form-builder',
								'liferay-form-field-base'
							]
						},
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