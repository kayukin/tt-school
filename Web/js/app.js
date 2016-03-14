var main_view;
$(document).ready(function () {
    main_view = $('.main-view');
    var token = Cookies.get('token');
    $.ajax({
        url: 'api/login',
        method: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers: {"token": token}
    }).done(function (data) {
        loadMainPage();
    }).fail(function (data) {
        loadLoginPage();
    });
});


var onFormSubmit = function (event) {
    event.preventDefault();
    var credentials = {login: this.login.value, password: this.password.value};
    $.ajax({
            url: 'api/login',
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(credentials)
        })
        .done(function (data) {
            token = data.token;
            Cookies.set('token', token, {expires: 7});
            loadMainPage();
        })
        .fail(function (data) {
            console.log(data);
        });
};

var loadLoginPage = function () {
    $('.main-view').load('signin.html', function () {
        $('form').submit(onFormSubmit);
        $('.bar-link').click(function (e) {
            loadSignUpPage();
        });
    });
};

var loadMainPage = function () {
    var token = Cookies.get('token');
    $.ajax({
        url: 'api/login',
        method: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers: {"token": token}
    }).done(function (user) {
        var html = '<div class="row"><div class="col-md-5">Добро пожаловать, ' + user.firstName + '</div></div>' +
            '<div class="row"><div class="dropdown"><button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" ' +
            'aria-expanded="false">Skills ' +
            '<span class="caret"></span> </button> ' +
            '<ul class="dropdown-menu" aria-labelledby="dLabel">';
        user.skills.forEach(function (value) {
            html += '<li>' + value.name + ' - ' + value.level + '</li>';
        });
        html += '</ul></div></div>';
        main_view.html(html);
    });
};

var loadSignUpPage = function () {
    main_view.load('signup.html', function () {
        $('form').submit(function (e) {
            e.preventDefault();
        });
    });
};