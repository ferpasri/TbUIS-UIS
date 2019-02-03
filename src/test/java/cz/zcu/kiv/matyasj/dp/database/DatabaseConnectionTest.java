package cz.zcu.kiv.matyasj.dp.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * DatabaseConnectionTest test suite
 *
 * @author Jiri Matyas
 */
public class DatabaseConnectionTest {
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * SetUp method prepare entityManager instance for UIS-web DB
     */
    @Before
    public void setUp() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cz.zcu.kiv.matyasj.dp");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * This method tests UIS-web - DB connection
     */
    @Test
    public void databaseConnectionTest() {
        Assert.assertNotNull(entityManager);
        List users = entityManager.createQuery("SELECT u FROM User u").getResultList();
        assertNotNull(users);
    }
}