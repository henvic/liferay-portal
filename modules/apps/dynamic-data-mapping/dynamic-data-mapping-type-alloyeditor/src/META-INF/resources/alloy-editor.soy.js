// This file was automatically generated from alloy-editor.soy.
// Please don't edit this file by hand.

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.alloyEditor = function(opt_data, opt_ignored) {
  return '\t<div class="form-group' + soy.$$escapeHtml(opt_data.visible ? '' : ' hide') + '" data-fieldname="' + soy.$$escapeHtml(opt_data.name) + '"><label class="control-label" for="' + soy.$$escapeHtml(opt_data.name) + '">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<b>*</b>' : '') + '</label><div class="field form-control"  id="' + soy.$$escapeHtml(opt_data.name) + '" name="' + soy.$$escapeHtml(opt_data.name) + '">' + soy.$$escapeHtml(opt_data.value) + '</div>' + soy.$$filterNoAutoescape(opt_data.childElementsHTML) + '</div>';
};
