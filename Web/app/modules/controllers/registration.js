'use strict';

var RegistrationView = require('../views/registration');
var store = require('../store');

module.exports = function () {
    var user = store.getUser();

    if (user.get('isAuth')) {
        this.navigate('', {trigger: true});
    }

    var registrationView = new RegistrationView({model: user});

    loginView.on('user:login', function (params) {
        var session = store.getSession();

        session.save(params, {
            success: function () {
                console.log('session:save created ', arguments);
            },
            error: function () {
                console.log('session:save error ', arguments);
                loginView.render({error: 'Wrong login or password'});
            }
        });
    });

    $('.Content').empty().append(loginView.render());
};
