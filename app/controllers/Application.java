package controllers;

import dao.IUserDao;
import dao.UserJpaDaoImpl;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

import static play.data.Form.form;

public class Application  extends Controller {
    static IUserDao userDao = new UserJpaDaoImpl();
    public static Result login() {
        return ok(
                login.render(form(User.class))
        );
    }
    @Transactional(readOnly=true)
    public static Result authenticate() {
        Form<User> loginForm = form(User.class)
                .bindFromRequest();
        String email = loginForm.get().email;
        String password = loginForm.get().password;
        if (userDao.find(email, password) == null){
            return forbidden("invalid password");
        }
        session().clear();
        session("email", email);
        return redirect(
                routes.Products.list()
        );
    }
}
