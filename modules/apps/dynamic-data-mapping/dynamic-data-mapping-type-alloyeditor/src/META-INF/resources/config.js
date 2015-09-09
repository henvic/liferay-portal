;(function() {
	var PATH_DDM_TYPE_ALLOY_EDITOR = Liferay.ThemeDisplay.getPathContext() + '/o/ddm-type-alloy-editor';

	AUI().applyConfig(
		{
			groups: {
				'field-alloy-editor': {
					base: PATH_DDM_TYPE_ALLOY_EDITOR + '/',
					modules: {
						'liferay-ddm-form-field-alloy-editor': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'alloy-editor_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-alloy-editor-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'alloy-editor.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: PATH_DDM_TYPE_ALLOY_EDITOR + '/'
				}
			}
		}
	);
})();