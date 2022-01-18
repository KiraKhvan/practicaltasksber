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
public class OrganizationControllerTest extends ControllerTestHelper {

    @Test
    public void saveTest() {
        if (checkApplicationEnable()) {
            JSONObject newOrganizationJson = new JSONObject();
            newOrganizationJson.put("name", "name");
            newOrganizationJson.put("fullName", "fullName");
            newOrganizationJson.put("inn", "inn");
            newOrganizationJson.put("kpp", "kpp");
            newOrganizationJson.put("address", "address");
            newOrganizationJson.put("phone", "phone");
            newOrganizationJson.put("isActive", true);

            HttpResponse response = sendHttpPostRequest("organization/save", newOrganizationJson);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void updateTest() {
        if (checkApplicationEnable()) {
            //Создаем фильтр
            JSONObject jsonFilter = new JSONObject();
            jsonFilter.put("name", "Test1");

            //Добавляем новую организацию
            JSONObject newOrganizationJson = new JSONObject();
            newOrganizationJson.put("name", "name");
            newOrganizationJson.put("fullName", "fullName");
            newOrganizationJson.put("inn", "inn");
            newOrganizationJson.put("kpp", "kpp");
            newOrganizationJson.put("address", "address");
            newOrganizationJson.put("phone", "phone");
            newOrganizationJson.put("isActive", true);
            sendHttpPostRequest("organization/save", newOrganizationJson);

            //Получаем список организаций по фильтру
            HttpResponse getByFilter = sendHttpPostRequest("organization/list", jsonFilter);
            List jsonArray = getJsonDataArray(getByFilter);
            //Проверяем что список пуст, так как еще нет организации с заданным именем
            Assert.assertTrue(CollectionUtils.isEmpty(jsonArray));


            JSONObject jsonFilterByName = new JSONObject();
            jsonFilterByName.put("name", "name");
            //Получаем список организаций по имени новой созданной организации
            HttpResponse getByName = sendHttpPostRequest("organization/list", jsonFilterByName);
            List jsonArray2 = getJsonDataArray(getByName);
            Map dataById = (Map) jsonArray2.get(0);
            String id = (String) dataById.get("id");

            //Добавляем новую организацию
            JSONObject editOrganizationJson = new JSONObject();
            editOrganizationJson.put("id", id);
            editOrganizationJson.put("name", "Test1");
            editOrganizationJson.put("fullName", "fullName");
            editOrganizationJson.put("inn", "inn");
            editOrganizationJson.put("kpp", "kpp");
            editOrganizationJson.put("address", "address");
            editOrganizationJson.put("phone", "phone");
            editOrganizationJson.put("isActive", true);

            HttpResponse editOrganizationResponse = sendHttpPostRequest("organization/update", editOrganizationJson);
            Assert.assertEquals(editOrganizationResponse.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void organizationsTest() {
        if (checkApplicationEnable()) {
            //Добавляем новую организацию
            JSONObject newOrganizationJson = new JSONObject();
            newOrganizationJson.put("name", "Test1");
            newOrganizationJson.put("fullName", "fullName");
            newOrganizationJson.put("inn", "inn");
            newOrganizationJson.put("kpp", "kpp");
            newOrganizationJson.put("address", "address");
            newOrganizationJson.put("phone", "phone");
            newOrganizationJson.put("isActive", true);
            sendHttpPostRequest("organization/save", newOrganizationJson);

            //Создаем фильтр
            JSONObject jsonFilter = new JSONObject();
            jsonFilter.put("name", "Test1");

            //Получаем список организаций по фильтру
            HttpResponse getByFilter = sendHttpPostRequest("organization/list", jsonFilter);
            List jsonArray = getJsonDataArray(getByFilter);
            //Проверяем что список не пуст
            Assert.assertFalse(CollectionUtils.isEmpty(jsonArray));
        }
    }

    @Test
    public void getOrganizationTest() {
        if (checkApplicationEnable()) {
            //Добавляем новую организацию
            JSONObject newOrganizationJson = new JSONObject();
            newOrganizationJson.put("name", "test4");
            newOrganizationJson.put("fullName", "fullName");
            newOrganizationJson.put("inn", "inn");
            newOrganizationJson.put("kpp", "kpp");
            newOrganizationJson.put("address", "address");
            newOrganizationJson.put("phone", "phone");
            newOrganizationJson.put("isActive", true);
            sendHttpPostRequest("organization/save", newOrganizationJson);

            //Создаем фильтр
            JSONObject jsonFilter = new JSONObject();
            jsonFilter.put("name", "test4");

            //Получаем список организаций по фильтру
            HttpResponse getByFilter = sendHttpPostRequest("organization/list", jsonFilter);
            List jsonArray = getJsonDataArray(getByFilter);
            Map map = (Map) jsonArray.get(0);
            String id = (String) map.get("id");

            //Получаем организацию по id
            HttpResponse getResponse = sendHttpGetRequest("organization/" + id, null);
            Map dataById = getJsonData(getResponse);
            Assert.assertNotNull(dataById);
        }
    }
}
