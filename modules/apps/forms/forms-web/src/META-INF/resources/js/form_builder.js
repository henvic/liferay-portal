AUI.add(
	'liferay-forms-form-builder',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var FormBuilder = A.Component.create(
			{
				ATTRS: {
				},

				EXTENDS: A.Layout,

				NAME: 'liferay-forms-form-builder',

				prototype: {
				}
			}
		);

		Liferay.namespace('Forms').FormBuilder = FormBuilder;

		Liferay.namespace('Forms').FormBuilder.Util = {
			getFieldTypes: function(fieldTypes) {
				var instance = this;

				var normalized = [];

				AArray.each(
					fieldTypes,
					function(item, index) {
						normalized.push(
							{
								defaultConfig: {
									advancedSettings: item.advancedSettings,
									basicSettings: item.basicSettings
								},
								fieldClass: A.Object.getValue(window, item.fieldClass.split('.')),
								icon: item.icon,
								label: item.label,
							}
						);
					}
				);

				return normalized;
			}
		};
	},
	'',
	{
		requires: ['aui-form-builder', 'liferay-forms-field-base']
	}
);