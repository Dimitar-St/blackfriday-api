const PORT = 8181,
      MAIN = 'http://localhost:' + PORT + '/blackfriday/api';

const API_ENDPOINTS = {
    USERS: MAIN + '/users',
    USERS_AUTH: MAIN + '/users/auth',

    PRODUCTS: MAIN + '/products'
}

export default API_ENDPOINTS;

