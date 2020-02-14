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

        requester.post(path, data, 'application/json')
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

        requester.post(path, data, 'application/json')
        .then((data) => {
           window.localStorage.setItem('token', data.token);
           window.localStorage.setItem('user_id', data.user_id);

           sammy.redirect('#/home');

           location.reload();
           notification('Log in successfully', 'success');
        })
        .catch((error) => {
           notification('Something got wrong!!', 'error');
           
           console.log(error);
        });
    }

    logout(sammy) {
        window.localStorage.setItem('token', '');
        window.localStorage.setItem('user_id', '');
        sammy.redirect('#/home');

        location.reload();
    }
    
    profile(sammy) {
    	let templatePath = './views/templates/profile.mustache',
    		id = window.localStorage.getItem('user_id'),
    		profilePath = API_ENDPOINTS.USERS + '/' + id,
    		authorizationHeaders = {
    			'Authorization': 'Bearer ' + window.localStorage.getItem('token')
    		};
    	
    	requester.get(templatePath)
    			 .then((template) => {
    				 requester.getProfile(profilePath, authorizationHeaders)
    				 		  .then((profile) => {
    				 			  let rendered = Mustache.render(template, { profile: profile });
    				 			  
    				 			  $('#content').html(rendered);
    				 		  })
    				 		  .catch((error) => {
    				 			  console.log(error);
    				 		  });
    			 });
    			 
    	
    	
    }

    order(sammy) {
        let id = '/' + sammy.path.split('/')[5],
            orderPath = API_ENDPOINTS.PRODUCTS + id  + '/orders',
            authorizatoinHeader = {
                'Authorization': 'Bearer ' + window.localStorage.getItem('token')
            };

        requester.order(orderPath, authorizatoinHeader)
                 .then((response) => {
                    notification('Ordered', 'success');

                    console.log(response);
                 }).catch((error) => {
                    notification(error.statusText, 'warning');
                    console.log(error);
                 }); 
        
        sammy.redirect('#/products' + id);
    }
}

const userController = new User();

export default userController;