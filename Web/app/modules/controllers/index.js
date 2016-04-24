'use strict';

var IndexView = require('../views/profile-view');

module.exports = function () {
    $('.Content').empty().append((new IndexView()).render());
};
