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
import project.dao.organization.OrganizationDao;
import project.dto.filter.organization.OrganizationFilter;
import project.dto.request.organization.AddOrganizationRequest;
import project.dto.request.organization.EditOrganizationRequest;
import project.model.Organization;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        AddOrganizationRequest request = new AddOrganizationRequest();
        request.name = "name";
        request.fullName = "fullName";
        request.inn = "inn";
        request.kpp = "kpp";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        Assert.assertTrue(organizationService.save(request));
    }

    @Test
    public void updateTest() {
        AddOrganizationRequest request = new AddOrganizationRequest();
        request.name = "name";
        request.fullName = "fullName";
        request.inn = "inn";
        request.kpp = "kpp";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        organizationService.save(request);

        Organization organization = organizationDao.loadByName("name");

        EditOrganizationRequest editOrganizationRequest = new EditOrganizationRequest();
        editOrganizationRequest.id = organization.getId();
        editOrganizationRequest.name = "name2";
        editOrganizationRequest.fullName = "fullName";
        editOrganizationRequest.inn = "inn";
        editOrganizationRequest.kpp = "kpp";
        editOrganizationRequest.address = "address";
        editOrganizationRequest.phone = "phone";
        editOrganizationRequest.isActive = true;
        Assert.assertTrue(organizationService.update(editOrganizationRequest));
        organization = em.find(Organization.class, organization.getId());

        Assert.assertEquals(organization.getName(), "name2");
    }

    @Test
    public void organizationsTest() {
        AddOrganizationRequest request = new AddOrganizationRequest();
        request.name = "name";
        request.fullName = "fullName";
        request.inn = "inn";
        request.kpp = "kpp";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        organizationService.save(request);

        Organization organization = organizationDao.loadByName("name");
        OrganizationFilter filter = new OrganizationFilter();
        filter.name = "name";

        Assert.assertEquals(organizationService.getOrganizations(filter).get(0).id, organization.getId().toString());
    }

    @Test
    public void getOrganizationTest() {
        AddOrganizationRequest request = new AddOrganizationRequest();
        request.name = "name";
        request.fullName = "fullName";
        request.inn = "inn";
        request.kpp = "kpp";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        organizationService.save(request);

        Organization organization = organizationDao.loadByName("name");

        Assert.assertNotNull(organizationService.getOrganization(organization.getId()));
    }
}
