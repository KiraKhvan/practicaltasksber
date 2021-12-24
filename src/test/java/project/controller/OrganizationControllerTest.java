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
import project.dao.organization.OrganizationDao;
import project.model.Organization;
import project.service.OrganizationService;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationControllerTest {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        Assert.assertTrue(
                organizationService.save(
                        "name",
                        "fullName",
                        "inn",
                        "kpp",
                        "address",
                        "phone",
                        true
                )
        );
    }

    @Test
    public void updateTest() {
        organizationService.save(
                "name",
                "fullName",
                "inn",
                "kpp",
                "address",
                "phone",
                true
        );
        Organization organization = organizationDao.loadByName("name");
        Assert.assertTrue(
                organizationService.update(
                        organization.getId().toString(),
                        "name2",
                        "fullName",
                        "inn",
                        "kpp",
                        "address",
                        "phone",
                        true
                )
        );
        organization = em.find(Organization.class, organization.getId());

        Assert.assertEquals(organization.getName(), "name2");
    }

    @Test
    public void organizationsTest() {
        organizationService.save(
                "name",
                "fullName",
                "inn",
                "kpp",
                "address",
                "phone",
                true
        );
        Organization organization = organizationDao.loadByName("name");

        Assert.assertEquals(
                organizationService.getOrganizations("name", null, null).get(0).id,
                organization.getId().toString()
        );
    }

    @Test
    public void getOrganizationTest() {
        organizationService.save(
                "name",
                "fullName",
                "inn",
                "kpp",
                "address",
                "phone",
                true
        );
        Organization organization = organizationDao.loadByName("name");

        Assert.assertNotNull(organizationService.getOrganization(organization.getId().toString()));
    }
}
