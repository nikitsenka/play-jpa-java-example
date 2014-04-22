package controllers;

import dao.IUserDao;
import dao.UserJpaDaoImpl;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registration;

public class Users extends Controller {
    static IUserDao userDao = new UserJpaDaoImpl();
    private static final Form<User> userForm = Form
            .form(User.class);
    @Transactional
    public static Result save(){
        Form<User> boundForm = userForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", Messages.get("error.form"));
            return badRequest(registration.render(boundForm));
        }
        User user = boundForm.get();
        User searchResult = userDao.find(user.email);
        if(searchResult != null){
            flash("error", Messages.get("error.user.exist"));
            return badRequest(registration.render(userForm));
        }
        userDao.create(user);
        flash("success",
                String.format(Messages.get("success.add"), user.email));
        return redirect(routes.Application.login());
    }
    @Transactional
    public static Result newUser(){
        return ok(registration.render(userForm));
    }
}
