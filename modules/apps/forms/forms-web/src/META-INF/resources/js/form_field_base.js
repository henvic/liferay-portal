AUI.add(
	'liferay-form-field-base',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var STR_BLANK = '';

		var FormLayout = A.Component.create(
			{
				ATTRS: {
					advancedSettings: {
						value: {}
					},

					basicSettings: {
						value: {}
					}
				},

				AUGMENTS: [A.FormBuilderFieldBase],

				EXTENDS: A.FormField,

				NAME: 'liferay-ddm-layout',

				prototype: {
				}
		};
	},
	'',
	{
		requires: ['aui-form-builder-page-break-row', 'aui-form-builder-field-text', 'aui-layout', 'liferay-ddm-form']
	}
);