;(function() {
	AUI().applyConfig(
		{
			groups: {
				forms: {
					base: '/o/comliferayformsweb/js/',
					modules: {
						'liferay-forms-definition-serializer': {
							path: 'form_definition_serializer.js',
							requires: [
								'liferay-forms-form-builder'
							]
						},
						'liferay-forms-field-base': {
							path: 'form_field_base.js',
							requires: [
								'aui-boolean-data-editor',
								'aui-form-builder-field-base',
								'aui-form-field',
								'aui-options-data-editor',
								'aui-radio-group-data-editor',
								'aui-tabs-data-editor',
								'aui-text-data-editor'
							]
						},
						'liferay-forms-form-builder': {
							path: 'form_builder.js',
							requires: [
								'aui-form-builder',
								'liferay-forms-field-base',
								'liferay-forms-layout-serializer'
							]
						},
						'liferay-forms-layout-serializer': {
							path: 'form_layout_serializer.js',
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
								'liferay-forms-layout-serializer',
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