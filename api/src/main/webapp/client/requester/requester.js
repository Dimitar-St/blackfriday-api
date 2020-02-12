class Requester {
    constructor( ){}

    get(url, type) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'GET',
                contentType: type,
                success: function(data) {
                    resolve(data);
                },
                error: function(error) {
                    reject(error);
                }
            });
        })
    }

    post(url, data , type, headers) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: JSON.stringify(data),
                headers: headers,
                contentType: type,
                dataType: 'json',
                success: function(data) {
                    resolve(data);
                },
                error: function(error) {
                    reject(error);
                }
            });
        });
    } 
    
    order(url, headers) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                headers: headers
            })
            .done((data) => {
            	resolve(data);
            })
            .fail((error) => {
            	reject(error);
            });
        });
    }
}

const requester = new Requester();

export default requester;