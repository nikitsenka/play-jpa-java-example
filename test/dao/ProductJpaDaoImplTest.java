package dao;

import helper.AbstractPlayTest;
import models.Product;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static helper.Assert.assertProductsEquals;
import static org.junit.Assert.assertTrue;

public class ProductJpaDaoImplTest extends AbstractPlayTest {
    IProductDao productDao = new ProductJpaDaoImpl();

    @Test
    public void testFindAll() throws Exception {
        Product expect = new Product("1234567891234", "name", "");
        em.persist(expect);
        List<Product> results = productDao.findAll();
        assertTrue(results.size()==1);
        assertProductsEquals(expect, results.get(0));
    }

    @Test
    public void testFindById() throws Exception {
        String ean = "1234567891234";
        Product expect = new Product(ean, "name", "");
        em.persist(expect);
        Product actual = productDao.findById(ean);
        assertProductsEquals(expect, actual);
    }

    @Test
    public void testCreateUpdate() throws Exception {
        Product expect = new Product("1234567891234", "name", "example");
        expect.picture = Files.readAllBytes(Paths.get("test/test.jpeg"));
        Product actual = productDao.createUpdate(expect);
        assertProductsEquals(expect, actual);
    }

    @Test
    public void testDelete() throws Exception {
        String ean = "1234567891234";
        Product product = new Product(ean, "name", "");
        em.persist(product);
        productDao.delete(ean);
        Product result = em.find(Product.class, ean);
        assertTrue(result == null);
    }
}
