package controllers;

import dao.IProductDao;
import dao.ProductHibernateDaoImpl;
import models.Product;
import play.*;
import play.mvc.*;
import views.html.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result hello(String name) {
        return ok("Hello " + name);
    }
}
