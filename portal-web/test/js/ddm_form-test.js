'use strict';

var assert = chai.assert;

describe('Simple Test', function() {
    this.timeout(5000);

    before(function(done) {
        var self = this;

        AUI().use('liferay-ddm-form', function(A) {
            assert.ok(Liferay.DDM.Form);
            assert.ok(themeDisplay);

            done();
        });
    });

    it('Should test if DDM Form exists', function(done) {
        assert.strictEqual('liferay-ddm-form', Liferay.DDM.Form.NAME);

        done();
    });

    // it('Should test if DDM Field exists', function(done) {
    //     assert.strictEqual('liferay-ddm-field', Liferay.DDM.Field.NAME);

    //     done();
    // });
});