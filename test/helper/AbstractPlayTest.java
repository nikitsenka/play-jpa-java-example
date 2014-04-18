package helper;

import org.junit.After;
import org.junit.Before;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import javax.persistence.EntityManager;

public class AbstractPlayTest {
    protected EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication();
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin =
                app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
    }

    @After
    public void tearDown() {
        JPA.bindForCurrentThread(null);
        em.close();
    }
}
