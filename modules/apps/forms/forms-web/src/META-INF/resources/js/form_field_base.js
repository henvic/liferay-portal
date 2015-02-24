AUI.add(
	'liferay-form-field-base',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var FormsFieldBase = A.Component.create(
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

				NAME: 'liferay-form-field-base',

				prototype: {
					_fillAdvancedSettings: function() {
						var instance = this;

						var advancedSettings = instance.get('advancedSettings');

						Array.prototype.push.apply(instance._advancedSettings, instance._normalizeSetitngs(advancedSettings));
					},

					_fillSettings: function() {
						var instance = this;

						var basicSettings = instance.get('basicSettings');

						Array.prototype.push.apply(instance._settings, instance._normalizeSetitngs(basicSettings));
					},

					_getEditor: function(editorType, editorOptions) {
						var instance = this;

						console.log(editorType + 'DataEditor', A[editorType + 'DataEditor']);

						return new A[editorType + 'DataEditor'](editorOptions);
					},

					_normalizeSetitngs: function(settings) {
						var instance = this;

						var normalized = [];

						AArray.each(
							settings,
							function(item, index) {
								normalized.push(
									{
										attrName: item.attrName,
										editor: instance._getEditor(item.editorType, item.editorOptions)
									}
								);
							}
						);

						return normalized;
					}
				}
			}
		);

		Liferay.namespace('Forms').FieldBase = FormsFieldBase;
	},
	'',
	{
		requires: ['aui-form-builder-field-base', 'aui-form-field', 'aui-boolean-data-editor', 'aui-options-data-editor', 'aui-tabs-data-editor', 'aui-radio-group-data-editor']
	}
);