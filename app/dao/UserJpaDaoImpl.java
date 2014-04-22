package dao;

import models.User;
import play.db.jpa.JPA;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserJpaDaoImpl implements IUserDao {
    @Override
    public User find(String email, String password) {
        TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u where u.email = :email and u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        try{
            return query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void create(User u) {
        JPA.em().persist(u);
    }

    @Override
    public User find(String email) {
        return JPA.em().find(User.class,email);
    }
}
