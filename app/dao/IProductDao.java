package dao;

import models.Product;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();
    Product findById(String id);
    void createUpdate(Product p);
    void delete(String id);
}
