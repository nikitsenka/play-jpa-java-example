package controllers;

import dao.IUserDao;
import dao.UserJpaDaoImpl;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.registration;

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
        if(loginForm.hasErrors()) {
            flash("error", Messages.get("error.form"));
            return badRequest(login.render(loginForm));
        }
        String email = loginForm.get().email;
        String password = loginForm.get().password;
        if (userDao.find(email, password) == null){
            return forbidden(Messages.get("error.form.pass"));
        }
        session().clear();
        session("email", email);
        return redirect(
                routes.Products.list()
        );
    }
}
