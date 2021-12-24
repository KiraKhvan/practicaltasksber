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
import project.dao.office.OfficeDao;
import project.model.Office;
import project.service.OfficeService;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeControllerTest {

    @Autowired
    private OfficeService officeService;


    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        Assert.assertTrue(
                officeService.save(
                        1L,
                        "Name",
                        "address",
                        "phone",
                        true
                )
        );
    }

    @Test
    public void updateTest() {
        Assert.assertTrue(
                officeService.save(
                        1L,
                        "Name",
                        "address",
                        "phone",
                        true
                )
        );

        Office office = officeDao.loadByName("Name");
        Assert.assertTrue(
                officeService.update(
                        office.getId(),
                        "name2",
                        "address",
                        "phone",
                        true
                )
        );
        office = em.find(Office.class, office.getId());

        Assert.assertEquals(office.getName(), "name2");
    }

    @Test
    public void officesTest() {
        officeService.save(
                1L,
                "Name",
                "address",
                "phone",
                true
        );
        Office office = officeDao.loadByName("Name");

        Assert.assertEquals(
                officeService.getOffices(1L, "Name", null, null).get(0).id,
                office.getId().toString()
        );
    }

    @Test
    public void officeTest() {
        officeService.save(
                1L,
                "Name",
                "address",
                "phone",
                true
        );
        Office office = officeDao.loadByName("Name");
        Assert.assertNotNull(officeService.getOffice(office.getId()));
    }
}
