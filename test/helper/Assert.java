package helper;

import models.Product;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Assert {
    public static void assertProductsEquals(Product expect, Product actual) {
        assertEquals(expect.ean,actual.ean);
        assertEquals(expect.name,actual.name);
        assertEquals(expect.description,actual.description);
        assertTrue(Arrays.equals(expect.picture, actual.picture));
    }

}
