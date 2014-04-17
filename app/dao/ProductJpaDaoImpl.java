package dao;


import models.Product;
import play.db.jpa.JPA;

import java.util.List;

public class ProductJpaDaoImpl implements IProductDao {

    @Override
    public List<Product> findAll() {
        return JPA.em().createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public Product findById(String id) {
        return JPA.em().find(Product.class, id);
    }

    @Override
    public void createUpdate(Product p) {
        JPA.em().merge(p);
    }

    @Override
    public void delete(String id) {
        Product p = findById(id);
        if(p != null){
            JPA.em().remove(p);
        }
    }


}
