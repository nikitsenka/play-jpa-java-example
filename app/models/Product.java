package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Product.findAll", query="SELECT c FROM Product c")
public class Product {
    @Id
    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;
    public Product() {}
    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }
    public String toString() {
        return String.format("%s - %s", ean, name);
    }
}
