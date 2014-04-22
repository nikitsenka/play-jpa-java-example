package helper;

import models.Product;
import models.User;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Assert {
    public static void assertProductsEquals(Product expect, Product actual) {
        assertNotNull(actual);
        assertEquals(expect.ean,actual.ean);
        assertEquals(expect.name,actual.name);
        assertEquals(expect.description,actual.description);
        assertTrue(Arrays.equals(expect.picture, actual.picture));
    }
    public static void assertUsersEquals(User expect, User actual){
        assertNotNull(actual);
        assertEquals(expect.email,actual.email);
        assertEquals(expect.password,actual.password);
    }
}
