import requester from '../requester/requester.js';
import API_ENDPOINTS from './endpoints.js';

function notification(text, type) {
    new Noty({
        type: type,
        layout: 'center',
        text: text,
        theme: 'metroui',
        timeout: 2000
    }).show();
}

class User {
    constructor() {}

    loadRegisterPage() {
        let path = './views/register.html';

        requester.get(path, 'text/hmtl')
                 .then((template) => {
                    $('#content').html(template);
                 });
    }

    loadLoginPage() {
        let path = './views/login.html';

        requester.get(path, 'text/html')
                 .then((template) => {
                    $('#content').html(template);
                 })
    }

    register() {
        let path = API_ENDPOINTS.USERS;

        let data = {
            username: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }

        requester.post(path, data, 'application/json', {})
                 .then((message) => {
                    notification(message, 'success');

                    sammy.redirect('#/login');
                 })
                 .catch((error) => {
                     console.log(error);
                    notification(error, 'error');
                 });
    }

    login(sammy) {
        let path = API_ENDPOINTS.USERS_AUTH;

        let data = {
            username: $('#username').val(),
            password: $('#password').val()
        };

        requester.post(path, data, 'application/json', {})
                 .then((data) => {
                    notification('Log in successfully', 'success');

                    window.localStorage.setItem('token', data);

                    sammy.redirect('#/home');
                 })
                 .catch((error) => {
                    notification('Invalid credentials', 'error');
                    
                    console.log(error);
                 });
    }
}

const userController = new User();

export default userController;