package project.service;

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
import project.dto.filter.user.UserFilter;
import project.dto.request.user.AddUserRequest;
import project.dto.request.user.EditUserRequest;
import project.model.User;

import javax.persistence.EntityManager;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        AddUserRequest request = new AddUserRequest();
        request.officeId = 1L;
        request.firstName = "firstName";
        request.lastName = "secondName";
        request.middleName = "middleName";
        request.position = "Сотрудник";
        request.phone = "phone";
        request.isIdentified = true;
        request.docName = "Удостоверение";
        request.docNumber = "docNumber";
        request.docDate = new Date();
        request.citizenshipCode = "000";
        Assert.assertTrue(userService.save(request));
    }

    @Test
    public void updateTest() {
        AddUserRequest request = new AddUserRequest();
        request.officeId = 1L;
        request.firstName = "firstName";
        request.lastName = "secondName";
        request.middleName = "middleName";
        request.position = "Сотрудник";
        request.phone = "phone";
        request.isIdentified = true;
        request.docName = "Удостоверение";
        request.docNumber = "docNumber";
        request.docDate = new Date();
        request.citizenshipCode = "000";
        userService.save(request);
        User user = (User) em.createQuery("SELECT u FROM User u where u.firstName = :firstName")
                .setParameter("firstName", "firstName")
                .getSingleResult();
        EditUserRequest editUserRequest = new EditUserRequest();
        editUserRequest.id = user.getId();
        editUserRequest.officeId = 1L;
        editUserRequest.firstName = "firstName2";
        editUserRequest.lastName = "secondName";
        editUserRequest.middleName = "middleName";
        editUserRequest.position = "Сотрудник";
        editUserRequest.phone = "phone";
        editUserRequest.docName = "Удостоверение";
        editUserRequest.docNumber = "docName";
        editUserRequest.docDate = new Date();
        editUserRequest.citizenshipCode = "000";
        editUserRequest.isIdentified = true;
        Assert.assertTrue(userService.update(editUserRequest));
        user = em.find(User.class, user.getId());

        Assert.assertEquals(user.getFirstName(), "firstName2");
    }

    @Test
    public void usersTest() {
        AddUserRequest request = new AddUserRequest();
        request.officeId = 1L;
        request.firstName = "firstName";
        request.lastName = "secondName";
        request.middleName = "middleName";
        request.position = "Сотрудник";
        request.phone = "phone";
        request.isIdentified = true;
        request.docName = "Удостоверение";
        request.docNumber = "docNumber";
        request.docDate = new Date();
        request.citizenshipCode = "000";
        userService.save(request);

        User user = (User) em.createQuery("SELECT u FROM User u where u.firstName = :firstName")
                .setParameter("firstName", "firstName")
                .getSingleResult();
        UserFilter filter = new UserFilter();
        filter.officeId = 1L;
        filter.firstName = "firstName";

        Assert.assertEquals(userService.getUsers(filter).get(0).id, user.getId().toString());
    }

    @Test
    public void userTest() {
    }
}
