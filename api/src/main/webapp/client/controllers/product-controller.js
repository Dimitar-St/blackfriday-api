import requester from '../requester/requester.js';
import API_ENDPOINTS from './endpoints.js';

class Product {
    constructor() {}

    loadProductsPage() {
        let templatePath = './views/templates/products.mustache';

        requester.get(templatePath)
                 .then((template) => {
                     requester.get(API_ENDPOINTS.PRODUCTS)
                              .then((products) => {
                                console.log(products)

                                let rendered = Mustache.render(template, { products: products });

                                $('#content').html(rendered);
                              });
                 }); 
    }

    loadCurrentProduct(sammy) {
      let id = sammy.path.split('/')[5],
          templatePath = './views/templates/product.mustache',
          apiRoute = API_ENDPOINTS.PRODUCTS + '/' + id;

      requester.get(templatePath)
               .then((template) => {
                requester.get(apiRoute)
                         .then((product) => {
                            let rendered = Mustache.render(template, { product: product });

                            $('#content').html(rendered);
                         })
                         .catch((error) => {
                        	 console.log(error);
                         });
                
               });
    }
}

const productController = new Product();

export default productController;