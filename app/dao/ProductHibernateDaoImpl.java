package dao;


import models.Product;
import play.db.jpa.JPA;

import java.util.List;

public class ProductHibernateDaoImpl implements IProductDao {

    @Override
    public List<Product> findAll() {
        return JPA.em().createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public Product findById(String id) {
        return JPA.em().find(Product.class, id);
    }

    @Override
    public void create(Product p) {
        JPA.em().persist(p);
    }

    @Override
    public void update(Product p) {
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
