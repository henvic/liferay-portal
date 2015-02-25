AUI.add(
	'liferay-forms-layout-serializer',
	function(A) {
		var AArray = A.Array;

		var LOCALIZABLE_FIELD_ATTRS = ['label', 'options', 'predefinedValue', 'style', 'tip'];

		var UNLOCALIZABLE_FIELD_ATTRS = ['dataType', 'fieldNamespace', 'indexType', 'localizable', 'multiple', 'name', 'readOnly', 'repeatable', 'required', 'showLabel', 'type'];

		var LayoutSerializer = {
			deserialize: function(layoutJSON, definitionJSON) {
				var instance = this;

				var rows = [];

				AArray.each(
					layoutJSON.rows,
					function(item, index) {
						var row = new A[item.type](
							{
								cols: instance.deserializeColumns(item.columns, definitionJSON)
							}
						);

						rows.push(row);
					}
				);

				return { rows: rows };
			},

			deserializeColumns: function(columns, definition) {
				var instance = this;

				var cols = [];

				AArray.each(
					columns,
					function(item, index) {
						var col = new A.LayoutCol(
							{
								size: item.size,
								value: instance.deserializeField(item.fieldName, definition)
							}
						);

						cols.push(col);
					}
				);

				return cols;
			},

			deserializeField: function(fieldName, definition) {
				var instance = this;

				var fieldInfo = Liferay.DDM.Form.Util.getFieldInfo(definition, 'name', fieldName);

				var type = instance.getType(fieldInfo.type);

				var array = type.fieldClass.split('.');

				return new A[array[1]]({
					name: fieldName
				});
			},

			getRowType: function(row) {
				var instance = this;

				var type;

				if (A.instanceOf(row, A.FormBuilderPageBreakRow)) {
					type = 'FormBuilderPageBreakRow';
				}
				else {
					type = 'LayoutRow';
				}

				return type;
			},

			getType: function(name) {
				return AArray.filter(
					Liferay.DDM.Types,
					function(item, index, collection) {
						return item.name === name;
					}
				)[0];
			},

			serialize: function(layout) {
				var instance = this;

				return {
					rows: instance.serializeRows(layout.get('rows'))
				};
			},

			serializeCols: function(columns) {
				var instance = this;

				return AArray.map(
					columns,
					function(item, index) {
						var fieldName = '';

						var value = item.get('value');

						if (A.instanceOf(value, A.FormField)) {
							fieldName = value.get('name');
						}

						return {
							fieldName: fieldName,
							size: item.get('size')
						}
					}
				);
			},

			serializeRows: function(rows) {
				var instance = this;

				return AArray.map(
					rows,
					function(item, index) {
						var row = {
							type: instance.getRowType(item)
						};

						if (row.type !== 'FormBuilderPageBreakRow') {
							row.cols = instance.serializeCols(item.get('cols'));
						}

						return row;
					}
				);
			}
		};

		Liferay.namespace('Forms').LayoutSerializer = LayoutSerializer;
	},
	'',
	{
		requires: ['aui-form-builder-page-break-row', 'aui-form-builder-field-text', 'aui-layout', 'liferay-ddm-form']
	}
);