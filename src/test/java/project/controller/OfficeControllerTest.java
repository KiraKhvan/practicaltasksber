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
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeControllerTest extends ControllerTestHelper {

    @Test
    public void saveTest() {
        if (checkApplicationEnable()) {
            JSONObject newOfficeJson = new JSONObject();
            newOfficeJson.put("orgId", 1L);
            newOfficeJson.put("name", "Test new office");
            newOfficeJson.put("address", "address");
            newOfficeJson.put("phone", "phone");
            newOfficeJson.put("isActive", true);

            HttpResponse response = sendHttpPostRequest("office/save", newOfficeJson);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void updateTest() {
        if (checkApplicationEnable()) {
            //Добавляем новый офис
            JSONObject newOfficeJson = new JSONObject();
            newOfficeJson.put("orgId", 1L);
            newOfficeJson.put("name", "name1");
            newOfficeJson.put("address", "address");
            newOfficeJson.put("phone", "phone");
            newOfficeJson.put("isActive", true);
            sendHttpPostRequest("office/save", newOfficeJson);

            //Получаем офис по имени
            JSONObject officeByNameJson = new JSONObject();
            officeByNameJson.put("name", "name1");
            HttpResponse getOfficeByNameResponse = sendHttpPostRequest("office/list", officeByNameJson);

            List list = getJsonDataArray(getOfficeByNameResponse);
            Map data = (Map) list.get(0);
            String id = (String) data.get("id");

            //Обновляем офис по id
            JSONObject updateOfficeJson = new JSONObject();
            updateOfficeJson.put("id", id);
            updateOfficeJson.put("name", "name2");
            updateOfficeJson.put("address", "address");
            updateOfficeJson.put("phone", "phone");
            updateOfficeJson.put("isActive", true);

            HttpResponse response = sendHttpPostRequest("office/update", updateOfficeJson);
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void officesTest() {
        if (checkApplicationEnable()) {
            //Создаем фильтр
            JSONObject jsonFilter = new JSONObject();
            jsonFilter.put("orgId", 1L);
            jsonFilter.put("name", "Test1");

            //Получаем список офисов по фильтру
            HttpResponse getOfficeByFilter = sendHttpPostRequest("office/list", jsonFilter);
            List jsonArray = getJsonDataArray(getOfficeByFilter);
            //Проверяем что список офисов пуст, так как еще нет офиса с заданным именем
            Assert.assertTrue(CollectionUtils.isEmpty(jsonArray));

            //Добавляем новый офис
            JSONObject newOfficeJson = new JSONObject();
            newOfficeJson.put("orgId", 1L);
            newOfficeJson.put("name", "Test1");
            newOfficeJson.put("address", "address");
            newOfficeJson.put("phone", "phone");
            newOfficeJson.put("isActive", true);
            sendHttpPostRequest("office/save", newOfficeJson);

            //Получаем список офисов по фильтру
            getOfficeByFilter = sendHttpPostRequest("office/list", jsonFilter);
            jsonArray = getJsonDataArray(getOfficeByFilter);
            //Список не пуст
            Assert.assertNotEquals(jsonArray.size(), 0);
        }
    }

    @Test
    public void officeTest() {
        if (checkApplicationEnable()) {
            //Добавляем новый офис
            JSONObject newOfficeJson = new JSONObject();
            newOfficeJson.put("orgId", 1L);
            newOfficeJson.put("name", "Test1");
            newOfficeJson.put("address", "address");
            newOfficeJson.put("phone", "phone");
            newOfficeJson.put("isActive", true);
            sendHttpPostRequest("office/save", newOfficeJson);


            //Получаем офис по имени
            JSONObject officeByNameJson = new JSONObject();
            officeByNameJson.put("name", "Test1");
            HttpResponse getOfficeByNameResponse = sendHttpPostRequest("office/list", officeByNameJson);

            List list = getJsonDataArray(getOfficeByNameResponse);
            Map dataByName = (Map) list.get(0);
            String id = (String) dataByName.get("id");

            //Получаем офис по id
            HttpResponse getOfficeIdNameResponse = sendHttpGetRequest("office/" + id, null);

            Map dataById = getJsonData(getOfficeIdNameResponse);
            Assert.assertNotNull(dataById);
        }
    }
}
