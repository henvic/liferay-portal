AUI.add(
	'liferay-forms-portlet',
	function(A) {
		var Lang = A.Lang;

		var FormsPortlet = A.Component.create(
			{
				ATTRS: {
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

						console.log('attaching form event');

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

						var form = Liferay.Form.get(instance.ns('fm'));

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
						}
					},

					_onDestroyPortlet: function(event) {
						var instance = this;

						instance.destroy();
					}
				}
			}
		);

		Liferay.namespace('Forms').Portlet = FormsPortlet;
	},
	'',
	{
		requires: ['aui-base', 'liferay-forms-steps', 'liferay-portlet-base']
	}
);