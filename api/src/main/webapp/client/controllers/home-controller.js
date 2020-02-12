import requester from '../requester/requester.js'

class Home {
    constructor() {}

    loadPage() {
        let path = './views/home.html';

        requester.get(path, 'text/html')
                 .then((html) => {
                    $('#content').html(html);
                 })
                 .catch((error) => {
                    console.log(error);
                 })
    }
}

const home = new Home();

export default home;