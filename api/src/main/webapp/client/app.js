import home from './controllers/home-controller.js';
import userController from './controllers/user-controller.js';
import productController from './controllers/product-controller.js';

let app = $.sammy('#content', function() {
    this.get('#/home', home.loadPage);

    this.get('#/register', userController.loadRegisterPage);
    this.get('#/login', userController.loadLoginPage);
    this.get('#/logout', userController.logout);

    this.post('#/register', userController.register);
    this.post('#/login', userController.login);

    // products
    this.get('#/products', productController.loadProductsPage);
    this.get('#/products/:id', productController.loadCurrentProduct);
    
    this.get('#/products/:id/orders', userController.order);
});

app.run('#/home');

function hide() {
    let token = window.localStorage.getItem('token');

    if (token.length > 10) {
        $('.profile').removeClass('invisible');
        $('.account').removeClass('invisible');

        $('.register').addClass('invisible');
        $('.login').addClass('invisible');
    
        console.log('logged');
      } else {
        $('.profile').addClass('invisible');
        $('.account').addClass('invisible');

        $('.register').removeClass('invisible');
        $('.login').removeClass('invisible');
    
        console.log('not logged');
      }
};

hide();