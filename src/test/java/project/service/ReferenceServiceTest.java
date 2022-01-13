package project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import project.Application;
import project.dao.country.CountryDao;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ReferenceServiceTest {

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EntityManager em;

    @Test
    public void test() {
    }
}
