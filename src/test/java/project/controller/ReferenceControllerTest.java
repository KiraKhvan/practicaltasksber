package project.controller;

import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import project.Application;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ReferenceControllerTest extends ControllerTest {

    @Test
    public void docsTest() {
        if (checkApplicationEnable()) {
            //Получаем список
            HttpResponse response = sendHttpGetRequest("docs", null);
            List jsonArray = getJsonDataArray(response);
            //Проверяем что список не пуст
            Assert.assertFalse(CollectionUtils.isEmpty(jsonArray));
        }
    }

    @Test
    public void countriesTest() {
        if (checkApplicationEnable()) {
            //Получаем список
            HttpResponse response = sendHttpGetRequest("countries", null);
            List jsonArray = getJsonDataArray(response);
            //Проверяем что список не пуст
            Assert.assertFalse(CollectionUtils.isEmpty(jsonArray));
        }
    }
}
