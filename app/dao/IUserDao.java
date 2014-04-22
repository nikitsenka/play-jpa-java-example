package dao;

import models.User;

public interface IUserDao {
    User find(String email, String password);
    void create(User u);
    User find(String email);
}
