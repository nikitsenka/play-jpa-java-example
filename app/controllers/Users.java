package controllers;

import dao.IUserDao;
import dao.UserJpaDaoImpl;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
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
            flash("error", "Please correct the form below.");
            return badRequest(registration.render(boundForm));
        }
        User user = boundForm.get();
        User searchResult = userDao.find(user.email);
        if(searchResult != null){
            flash("error", "The user with the same email already exist");
            return badRequest(registration.render(userForm));
        }
        userDao.create(user);
        flash("success",
                String.format("Successfully added user %s", user.email));
        return redirect(routes.Application.login());
    }
    @Transactional
    public static Result newUser(){
        return ok(registration.render(userForm));
    }
}
