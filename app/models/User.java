package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="User.findByEmailAndPass", query="SELECT u FROM User u where u.email = :email and u.password = :password")
public class User{
    @Id
    @Constraints.Email
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String password;
    public User() {
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
