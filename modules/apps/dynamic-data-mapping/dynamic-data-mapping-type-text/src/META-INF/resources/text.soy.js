// This file was automatically generated from text.soy.
// Please don't edit this file by hand.

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.text = function(opt_data, opt_ignored) {
return '\t<div class="form-group' + soy.$$escapeHtml(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtml(opt_data.name) + '"><label class="control-label" for="' + soy.$$escapeHtml(opt_data.name) + '">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<b>*</b>' : '') + '</label><div class="input-group-container ' + ((opt_data.tip) ? 'input-group-default' : '') + '"><input class="field form-control" dir="' + soy.$$escapeHtml(opt_data.dir) + '" id="' + soy.$$escapeHtml(opt_data.name) + '" name="' + soy.$$escapeHtml(opt_data.name) + '" placeholder="' + soy.$$escapeHtml(opt_data.placeholder) + '" type="text" value="' + soy.$$escapeHtml(opt_data.value) + '">' + ((opt_data.tip) ? '<span class="input-group-addon"><span class="input-group-addon-content"><a class="help-icon help-icon-default icon-question icon-monospaced" data-toggle="popover" data-original-title="' + soy.$$escapeHtml(opt_data.tip) + '" href="javascript:;" title="' + soy.$$escapeHtml(opt_data.tip) + '"></a></span></span>' : '') + '</div>' + soy.$$filterNoAutoescape(opt_data.childElementsHTML) + '</div>';
};