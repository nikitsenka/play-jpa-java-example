package dao;

import models.Product;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();
    Product findById(String id);
    void create(Product p);
    void update(Product p);
    void delete(String id);
}
