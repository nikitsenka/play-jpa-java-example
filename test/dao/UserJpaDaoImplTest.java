package dao;

import helper.AbstractPlayTest;
import models.User;
import org.junit.Test;

import static helper.Assert.assertUsersEquals;

public class UserJpaDaoImplTest extends AbstractPlayTest{
    private IUserDao userDao = new UserJpaDaoImpl();
    @Test
    public void testFindByEmailAndPass() throws Exception {
        String email = "email@mail.com";
        String password = "password";
        User user = new User(email, password);
        em.persist(user);
        User result = userDao.find(email, password);
        assertUsersEquals(user,result);
    }
    @Test
    public void create(){
        String email = "email@mail.com";
        String password = "password";
        User user = new User(email, password);
        userDao.create(user);
        User result = em.find(User.class, email);
        assertUsersEquals(user,result);
    }
    @Test
    public void testFindById(){
        String email = "email@mail.com";
        String password = "password";
        User user = new User(email, password);
        em.persist(user);
        User result = userDao.find(email);
        assertUsersEquals(user,result);
    }
}