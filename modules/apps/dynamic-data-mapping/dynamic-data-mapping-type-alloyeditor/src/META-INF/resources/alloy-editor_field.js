AUI.add(
	'liferay-ddm-form-field-alloy-editor',
	function(A) {
		var AlloyEditorField = A.Component.create(
			{
				ATTRS: {
					type: {
						value: 'alloyEditor'
					},

					value: {
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-alloy-editor',

				prototype: {
					destructor: function() {
						var editor = instance._alloyEditor;

						if (editor) {
							editor.destroy();
						}
					},

					render: function() {
						AlloyEditorField.superclass.render.apply(this, arguments);

						if (this._alloyEditor) {
							this._alloyEditor.destroy();
						}

						this._alloyEditor = AlloyEditor.editable(this.get('container').one('.field')._node);
					},

					getTemplateContext: function() {
						var instance = this;

						var value = instance.get('value');

						if (instance.get('localizable')) {
							value = value[instance.get('locale')];
						}

						return A.merge(
							AlloyEditorField.superclass.getTemplateContext.apply(instance, arguments),
							{
							}
						);
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').AlloyEditor = AlloyEditorField;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-field']
	}
);
