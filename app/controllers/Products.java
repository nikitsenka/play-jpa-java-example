package controllers;

import dao.IProductDao;
import dao.ProductJpaDaoImpl;
import models.Product;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.details;
import views.html.list;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
@Security.Authenticated(Secured.class)
public class Products extends Controller {

    private static IProductDao productDao = new ProductJpaDaoImpl();
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
        return ok(details.render(productForm.fill(product)));
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
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart part = body.getFile("picture");
        if(part != null) {
            File picture = part.getFile();
            try {
                Path path = Paths.get(picture.getPath());
                product.picture = Files.readAllBytes(path);
            } catch (IOException e) {
                return internalServerError("Error reading file upload");
            }
        }
        productDao.createUpdate(product);
        flash("success",
                String.format("Successfully added product %s", product));
        return redirect(routes.Products.list());
    }
    @Transactional
    public static Result delete(String ean){
        productDao.delete(ean);
        return redirect(routes.Products.list());
    }
    @Transactional
    public static Result picture(String ean) {
        final Product product = productDao.findById(ean);
        if(product == null) return notFound();
        return ok(product.picture);
    }
}
