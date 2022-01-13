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
import project.dao.office.OfficeDao;
import project.dto.filter.office.OfficeFilter;
import project.dto.request.office.AddOfficeRequest;
import project.dto.request.office.EditOfficeRequest;
import project.model.Office;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeServiceTest {
    @Autowired
    private OfficeService officeService;


    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        AddOfficeRequest request = new AddOfficeRequest();
        request.orgId = 1L;
        request.address = "address";
        request.name = "Name";
        request.phone = "phone";
        request.isActive = true;
        Assert.assertTrue(officeService.save(request));
    }

    @Test
    public void updateTest() {
        AddOfficeRequest request = new AddOfficeRequest();
        request.orgId = 1L;
        request.name = "Name";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        Assert.assertTrue(officeService.save(request));

        Office office = officeDao.loadByName("Name");

        EditOfficeRequest editOfficeRequest = new EditOfficeRequest();
        editOfficeRequest.id = office.getId();
        editOfficeRequest.name = "name2";
        editOfficeRequest.address = "address";
        editOfficeRequest.phone = "phone";
        editOfficeRequest.isActive = true;
        Assert.assertTrue(officeService.update(editOfficeRequest));
        office = em.find(Office.class, office.getId());

        Assert.assertEquals(office.getName(), "name2");
    }

    @Test
    public void officesTest() {
        AddOfficeRequest request = new AddOfficeRequest();
        request.orgId = 1L;
        request.name = "Name";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        officeService.save(request);
        Office office = officeDao.loadByName("Name");

        OfficeFilter filter = new OfficeFilter();
        filter.orgId = 1L;
        filter.name = "Name";

        Assert.assertEquals(officeService.getOffices(filter).get(0).id, office.getId().toString());
    }

    @Test
    public void officeTest() {
        AddOfficeRequest request = new AddOfficeRequest();
        request.orgId = 1L;
        request.name = "Name";
        request.address = "address";
        request.phone = "phone";
        request.isActive = true;
        officeService.save(request);
        Office office = officeDao.loadByName("Name");
        Assert.assertNotNull(officeService.getOffice(office.getId()));
    }
}
