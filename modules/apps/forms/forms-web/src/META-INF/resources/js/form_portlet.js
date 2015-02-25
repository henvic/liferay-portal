AUI.add(
	'liferay-forms-portlet',
	function(A) {
		var Lang = A.Lang;
		var LayoutSerializer = Liferay.Forms.LayoutSerializer;

		var FormsPortlet = A.Component.create(
			{
				ATTRS: {
					formName: {
						setter: 'ns'
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'liferay-forms-portlet',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance.bindUI();
					},

					bindUI: function() {
						var instance = this;

						instance._eventHandlers = [
							Liferay.after('form:registered', A.bind(instance._afterFormRegistered, instance)),
							Liferay.on('destroyPortlet', A.bind(instance._onDestroyPortlet, instance))
						];
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandlers)).detach();
					},

					_afterFormRegistered: function(event) {
						var instance = this;

						var form = Liferay.Form.get(instance.get('formName'));;

						if (form === event.form) {
							Liferay.component(
								instance.ns('FormSteps'),
								function() {
									return new Liferay.Forms.Steps(
										{
											form: form,
											namespace: instance.get('namespace'),
											tabView: Liferay.component(instance.ns('fmTabview'))
										}
									);
								}
							);

							Liferay.component(instance.ns('FormSteps')).syncUI();

							form.set('onSubmit', A.bind(instance._onSubmit, instance));
						}
					},

					_onDestroyPortlet: function(event) {
						var instance = this;

						instance.destroy();
					},

					_onSubmit: function(event) {
						var instance = this;

						event.preventDefault();

						var formBuilder = Liferay.component(instance.ns('FormBuilder'));

						var layout = formBuilder.get('layout');

						var layoutInput = instance.one('#layout');

						layoutInput.val(A.JSON.stringify(LayoutSerializer.serialize(layout)));

						console.log(layoutInput.val());
					}
				}
			}
		);

		Liferay.namespace('Forms').Portlet = FormsPortlet;
	},
	'',
	{
		requires: ['aui-base', 'json', 'liferay-forms-layout-serializer', 'liferay-forms-steps', 'liferay-portlet-base']
	}
);