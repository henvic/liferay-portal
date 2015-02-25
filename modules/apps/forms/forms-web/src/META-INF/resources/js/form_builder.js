AUI.add(
	'liferay-forms-form-builder',
	function(A) {
		var AArray = A.Array;

		var FormBuilder = A.Component.create(
			{
				ATTRS: {
				},

				CSS_PREFIX: 'form-builder',

				EXTENDS: A.FormBuilder,

				NAME: 'liferay-forms-form-builder',

				prototype: {
				}
			}
		);

		Liferay.namespace('Forms').FormBuilder = FormBuilder;

		Liferay.namespace('Forms').FormBuilder.Util = {
			getFieldClass: function(advancedSettings, basicSettings) {
				var instance = this;

				var attrs = {};

				AArray.each(
					advancedSettings.concat(basicSettings),
					function(item, index) {
						var value = '';

						if (item.editorType === 'RadioGroup') {
							value = undefined;
						}

						attrs[item.attrName] = {
							value: value
						};
					}
				);

				return A.Component.create(
					{
						ATTRS: attrs,

						EXTENDS: Liferay.Forms.FieldBase,

						NAME: 'liferay-form-field'
					}
				);
			},

			getFieldTypes: function(fieldTypes) {
				var instance = this;

				return AArray.map(
					fieldTypes,
					function(item, index) {
						return {
							defaultConfig: {
								advancedSettings: item.advancedSettings,
								basicSettings: item.basicSettings
							},
							fieldClass: instance.getFieldClass(
								item.advancedSettings,
								item.basicSettings
							),
							icon: item.icon,
							label: item.label,
						};
					}
				);
			}
		};
	},
	'',
	{
		requires: ['aui-form-builder', 'liferay-forms-field-base']
	}
);