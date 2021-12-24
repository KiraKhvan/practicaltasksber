package project.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import project.Application;
import project.dao.user.UserDao;
import project.model.User;
import project.service.UserService;

import javax.persistence.EntityManager;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        Assert.assertTrue(
                userService.save(
                        1L,
                        "firstName",
                        "secondName",
                        "middleName",
                        "Сотрудник",
                        "phone",
                        "docCode",
                        "Удостоверение",
                        "docNumber",
                        new Date(),
                        "000",
                        true
                )
        );
    }

    @Test
    public void updateTest() {
        userService.save(
                1L,
                "firstName",
                "secondName",
                "middleName",
                "Сотрудник",
                "phone",
                "docCode",
                "Удостоверение",
                "docNumber",
                new Date(),
                "000",
                true
        );
        User user = (User) em.createQuery("SELECT u FROM User u where u.firstName = :firstName")
                .setParameter("firstName", "firstName")
                .getSingleResult();

        Assert.assertTrue(
                userService.update(
                        user.getId(),
                        1L,
                        "firstName2",
                        "secondName",
                        "middleName",
                        "Сотрудник",
                        "phone",
                        "Удостоверение",
                        "docName",
                        new Date(),
                        "000",
                        true
                )
        );
        user = em.find(User.class, user.getId());

        Assert.assertEquals(user.getFirstName(), "firstName2");
    }

    @Test
    public void usersTest() {
        userService.save(
                1L,
                "firstName",
                "secondName",
                "middleName",
                "Сотрудник",
                "phone",
                "docCode",
                "Удостоверение",
                "docNumber",
                new Date(),
                "000",
                true
        );

        User user = (User) em.createQuery("SELECT u FROM User u where u.firstName = :firstName")
                .setParameter("firstName", "firstName")
                .getSingleResult();

        Assert.assertEquals(
                userService.getUsers(1L, "firstName", null, null, null, null, null).get(0).id,
                user.getId().toString()
        );
    }

    @Test
    public void userTest() {
    }
}
