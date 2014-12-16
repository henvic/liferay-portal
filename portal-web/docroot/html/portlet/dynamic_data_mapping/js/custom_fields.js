AUI.add(
	'liferay-portlet-dynamic-data-mapping-custom-fields',
	function(A) {
		/**
		 * The Form Field Html Text Component
		 *
		 * @module aui-form-field-html-text
		 */

		var CSS_FIELD_HTML_TEXT = A.getClassName('form', 'builder', 'field', 'html-text'),
		    CSS_FIELD_HTML_TEXT_INPUT = A.getClassName('form', 'builder', 'field', 'html-text', 'input'),
		    TPL_HTML_TEXT = '<textarea class="form-control form-builder-field-node lfrtext-html"></textarea>';

		/**
		 * A base class for `A.FormFieldHtmlText`.
		 *
		 * @class A.FormFieldHtmlText
		 * @extends A.FormField
		 * @param {Object} config Object literal specifying widget configuration
		 *     properties.
		 * @constructor
		 */
		A.FormFieldHtmlText = A.Base.create('form-field-text', A.FormField, [], {
		    TPL_FIELD_CONTENT: '<div class="' + CSS_FIELD_HTML_TEXT_INPUT + '"></div>',

		    /**
		     * Constructor for the `A.FormFieldHtmlText`. Lifecycle.
		     *
		     * @method initializer
		     * @protected
		     */
		    initializer: function() {
		        var content = this.get('content');

		        content.addClass(CSS_FIELD_HTML_TEXT);

		        var inputNode = this.get('content').one('.' + CSS_FIELD_HTML_TEXT_INPUT);

		        inputNode.append(TPL_HTML_TEXT);
		    }
		}, {
		    /**
		     * Static property used to define the default attribute configuration
		     * for the `A.FormFieldHtmlText`.
		     *
		     * @property ATTRS
		     * @type Object
		     * @static
		     */
		    ATTRS: {
		        /**
		         * Flag indicating if the text input will allow multiple lines.
		         *
		         * @attribute multiline
		         * @default false
		         * @type {Boolean}
		         */
		        multiline: {
		            validator: A.Lang.isBoolean,
		            value: false
		        },

		        /**
		         * Flag indicating if this field is required.
		         *
		         * @attribute required
		         * @default false
		         * @type {Boolean}
		         */
		        required: {
		            validator: A.Lang.isBoolean,
		            value: false
		        }
		    }
		});

		/**
		 * The Form Builder Field Html Text Component
		 *
		 * @module aui-form-builder
		 * @submodule aui-form-builder-field-html-text
		 */

		/**
		 * A base class for Form Builder Field Html Text.
		 *
		 * @class A.FormBuilderFieldHtmlText
		 * @extends A.FormFieldHtmlText
		 * @uses A.FormBuilderFieldBase
		 * @param {Object} config Object literal specifying widget configuration
		 *     properties.
		 * @constructor
		 */
		A.FormBuilderFieldHtmlText = A.Base.create('form-builder-field-html-text', A.FormFieldHtmlText, [A.FormBuilderFieldBase], {
		    /**
		     * Fills the settings array with the information for this field.
		     *
		     * @method _fillSettings
		     * @override
		     * @protected
		     */
		    _fillSettings: function() {
		        this._settings.push(
		            {
		                attrName: 'required',
		                editor: new A.BooleanDataEditor({
		                    label: 'Required'
		                })
		            },
		            {
		                attrName: 'multiline',
		                editor: new A.BooleanDataEditor({
		                    label: 'Multiline'
		                })
		            }
		        );
		    }
		});

		/**
         * The Form Field Link to Page Component
         *
         * @module aui-form-field-link-to-page
         */

        var CSS_FIELD_LINK_TO_PAGE = A.getClassName('form', 'builder', 'field', 'link-to-page'),
            CSS_FIELD_LINK_TO_PAGE_INPUT = A.getClassName('form', 'builder', 'field', 'link-to-page', 'input'),
            TPL_LINK_TO_PAGE = '<a href="javascript:;">Link</a>';

        /**
         * A base class for `A.FormFieldLinkToPage``.
         *
         * @class A.FormFieldLinkToPage
         * @extends A.FormField
         * @param {Object} config Object literal specifying widget configuration
         *     properties.
         * @constructor
         */
        A.FormFieldLinkToPage = A.Base.create('form-field-link-to-page', A.FormField, [], {
            TPL_FIELD_CONTENT: '<div class="' + CSS_FIELD_LINK_TO_PAGE_INPUT + '"></div>',

            /**
             * Constructor for the `A.FormFieldLinkToPage`. Lifecycle.
             *
             * @method initializer
             * @protected
             */
            initializer: function() {
                var content = this.get('content');
                var inputNode = this.get('content').one('.' + CSS_FIELD_LINK_TO_PAGE_INPUT);

                content.addClass(CSS_FIELD_LINK_TO_PAGE);

                inputNode.append(TPL_LINK_TO_PAGE);
            }
        }, {
            /**
             * Static property used to define the default attribute configuration
             * for the `A.FormFieldLinkToPage`.
             *
             * @property ATTRS
             * @type Object
             * @static
             */
            ATTRS: {
                /**
                 * Flag indicating if this field is required.
                 *
                 * @attribute required
                 * @default false
                 * @type {Boolean}
                 */
                required: {
                    validator: A.Lang.isBoolean,
                    value: false
                },

                dataType: {
                    value: 'link-to-page'
                },

                fieldNamespace: {
                    value: ''
                }
            }
        });

		/**
		 * The Form Builder Field Link To Page Component
		 *
		 * @module aui-form-builder
		 * @submodule aui-form-builder-field-link-to-page
		 */

		/**
		 * A base class for Form Builder Field Link To Page.
		 *
		 * @class A.FormBuilderFieldText
		 * @extends A.FormField
		 * @uses A.FormBuilderFieldBase
		 * @param {Object} config Object literal specifying widget configuration
		 *     properties.
		 * @constructor
		 */
		A.FormBuilderFieldLinkToPage = A.Base.create('form-builder-field-link-to-page', A.FormFieldLinkToPage, [A.FormBuilderFieldBase], {
		    /**
		     * Fills the settings array with the information for this field.
		     *
		     * @method _fillSettings
		     * @override
		     * @protected
		     */
		    _fillSettings: function() {
		        this._settings.push(
		            {
		                attrName: 'required',
		                editor: new A.BooleanDataEditor({
		                    label: 'Required'
		                })
		            }
		        );
		    }
		});

		/**
		 * The Form Field Geolocation Component
		 *
		 * @module aui-form-field-geolocation
		 */

		var CSS_FIELD_GEOLOCATION = A.getClassName('form', 'builder', 'field', 'geolocation'),
		    CSS_FIELD_GEOLOCATION_INPUT = A.getClassName('form', 'builder', 'field', 'geolocation', 'input'),
		    TPL_GEOLOCATION = '<input type="button" value="Select a geolocation" class="form-control"></input>';

		/**
		 * A base class for `A.FormFieldGeolocation`.
		 *
		 * @class A.FormFieldGeolocation
		 * @extends A.FormField
		 * @param {Object} config Object literal specifying widget configuration
		 *     properties.
		 * @constructor
		 */
		A.FormFieldGeolocation = A.Base.create('form-field-geolocation', A.FormField, [], {
		    TPL_FIELD_CONTENT: '<div class="' + CSS_FIELD_GEOLOCATION_INPUT + '"></div>',

		    /**
		     * Constructor for the `A.FormFieldGeolocation`. Lifecycle.
		     *
		     * @method initializer
		     * @protected
		     */
		    initializer: function() {
		        var inputNode = this.get('content').one('.' + CSS_FIELD_GEOLOCATION_INPUT);
		        var content = this.get('content');

		        content.addClass(CSS_FIELD_GEOLOCATION);

		        inputNode.append(TPL_GEOLOCATION);
		    }
		}, {
		    /**
		     * Static property used to define the default attribute configuration
		     * for the `A.FormFieldGeolocation`.
		     *
		     * @property ATTRS
		     * @type Object
		     * @static
		     */
		    ATTRS: {
		        /**
		         * Flag indicating if this field is required.
		         *
		         * @attribute required
		         * @default false
		         * @type {Boolean}
		         */
		        required: {
		            validator: A.Lang.isBoolean,
		            value: false
		        }
		    }
		});

		/**
		 * The Form Builder Field Geolocation Component
		 *
		 * @module aui-form-builder
		 * @submodule aui-form-builder-field-geolocation
		 */

		/**
		 * A base class for Form Builder Geolocation.
		 *
		 * @class A.FormBuilderFieldGeolocation
		 * @extends A.FormField
		 * @uses A.FormBuilderFieldBase
		 * @param {Object} config Object literal specifying widget configuration
		 *     properties.
		 * @constructor
		 */
		A.FormBuilderFieldGeolocation = A.Base.create('form-builder-field-geolocation', A.FormFieldGeolocation, [A.FormBuilderFieldBase], {
		    /**
		     * Fills the settings array with the information for this field.
		     *
		     * @method _fillSettings
		     * @override
		     * @protected
		     */
		    _fillSettings: function() {
		        this._settings.push(
		            {
		                attrName: 'required',
		                editor: new A.BooleanDataEditor({
		                    label: 'Required'
		                })
		            }
		        );
		    }
		});
	},
	'',
	{
		requires: ['liferay-portlet-dynamic-data-mapping']
	}
);