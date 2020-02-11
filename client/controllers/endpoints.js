const PORT = 8181,
      MAIN = 'http://localhost:' + PORT + '/api';

const API_ENDPOINTS = {
    USERS: MAIN + '/users',
    USERS_AUTH: MAIN + '/users/auth'
}

export default API_ENDPOINTS;

