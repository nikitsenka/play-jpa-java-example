package controllers;

import dao.IProductDao;
import dao.ProductHibernateDaoImpl;
import models.Product;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;
import views.html.list;

import java.util.Arrays;
import java.util.List;

public class Products extends Controller {

    private static IProductDao productDao = new ProductHibernateDaoImpl();
    private static final Form<Product> productForm = Form
            .form(Product.class);
    @Transactional(readOnly=true)
    public static Result list() {
        List<Product> products = productDao.findAll();
        return ok(list.render(products));
    }
    @Transactional(readOnly=true)
    public static Result details(String ean){
        Product product = productDao.findById(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }
        List<Product> result = Arrays.asList(product);
        return ok(list.render(result));
    }
    @Transactional
    public static Result newProduct(){
        return ok(details.render(productForm));
    }
    @Transactional
    public static Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        productDao.create(product);
        flash("success",
                String.format("Successfully added product %s", product));
        return redirect(routes.Products.list());
    }
    @Transactional
    public static Result delete(String ean){
        productDao.delete(ean);
        return redirect(routes.Products.list());
    }

}
