'use strict';

var IndexView = require('../views/profile-view');
var store = require('../store');

module.exports = function () {
    if (!store.getUser().get('isAuth')) {
        store.getRouter().navigate('login', {trigger: true});
        return;
    }
    $('.Content').empty().append((new IndexView()).render());
};
