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
public class UserControllerTest extends ControllerTest {

    @Test
    public void saveTest() {
        if (checkApplicationEnable()) {
            JSONObject newUserJson = new JSONObject();
            newUserJson.put("officeId", 1L);
            newUserJson.put("firstName", "firstName");
            newUserJson.put("lastName", "secondName");
            newUserJson.put("middleName", "middleName");
            newUserJson.put("position", "Сотрудник");
            newUserJson.put("phone", "phone");
            newUserJson.put("isIdentified", true);
            newUserJson.put("docName", "Удостоверение");
            newUserJson.put("docDate", "2018-09-04");
            newUserJson.put("citizenshipCode", "000");

            HttpResponse response = sendHttpPostRequest("user/save", newUserJson);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void updateTest() {
        if (checkApplicationEnable()) {
            //Добавляем нового пользователя
            JSONObject newUserJson = new JSONObject();
            newUserJson.put("officeId", 1L);
            newUserJson.put("firstName", "firstName");
            newUserJson.put("lastName", "secondName");
            newUserJson.put("middleName", "middleName");
            newUserJson.put("position", "Сотрудник");
            newUserJson.put("phone", "phone");
            newUserJson.put("isIdentified", true);
            newUserJson.put("docName", "Удостоверение");
            newUserJson.put("docDate", "2018-09-04");
            newUserJson.put("citizenshipCode", "000");
            sendHttpPostRequest("user/save", newUserJson);

            JSONObject jsonFilterByName = new JSONObject();
            jsonFilterByName.put("officeId", 1L);
            jsonFilterByName.put("firstName", "firstName");
            //Получаем список по имени
            HttpResponse getByName = sendHttpPostRequest("user/list", jsonFilterByName);
            List jsonArray2 = getJsonDataArray(getByName);
            Map dataById = (Map) jsonArray2.get(0);
            String id = (String) dataById.get("id");

            JSONObject updateUserJson = new JSONObject();
            updateUserJson.put("officeId", 1L);
            updateUserJson.put("id", id);
            updateUserJson.put("firstName", "firstName2");
            updateUserJson.put("lastName", "secondName");
            updateUserJson.put("middleName", "middleName");
            updateUserJson.put("position", "Сотрудник");
            updateUserJson.put("phone", "phone");
            updateUserJson.put("isIdentified", true);
            updateUserJson.put("docName", "Удостоверение");
            updateUserJson.put("docDate", "2018-09-04");
            updateUserJson.put("citizenshipCode", "000");
            HttpResponse editResponse = sendHttpPostRequest("user/update", updateUserJson);
            Assert.assertEquals(editResponse.getStatusLine().getStatusCode(), 200);
        }
    }

    @Test
    public void usersTest() {
        if (checkApplicationEnable()) {
            //Добавляем нового пользователя
            JSONObject newUserJson = new JSONObject();
            newUserJson.put("officeId", 1L);
            newUserJson.put("firstName", "firstName22");
            newUserJson.put("lastName", "secondName");
            newUserJson.put("middleName", "middleName");
            newUserJson.put("position", "Сотрудник");
            newUserJson.put("phone", "phone");
            newUserJson.put("isIdentified", true);
            newUserJson.put("docName", "Удостоверение");
            newUserJson.put("docDate", "2018-09-04");
            newUserJson.put("citizenshipCode", "000");
            sendHttpPostRequest("user/save", newUserJson);

            JSONObject jsonFilter = new JSONObject();
            jsonFilter.put("officeId", 1L);
            jsonFilter.put("firstName", "firstName22");
            //Получаем список
            HttpResponse getByName = sendHttpPostRequest("user/list", jsonFilter);
            List jsonArray = getJsonDataArray(getByName);
            //Проверяем что список не пуст
            Assert.assertFalse(CollectionUtils.isEmpty(jsonArray));
        }
    }

    @Test
    public void userTest() {
        if (checkApplicationEnable()) {
            //Добавляем нового пользователя
            JSONObject newUserJson = new JSONObject();
            newUserJson.put("officeId", 1L);
            newUserJson.put("firstName", "firstName");
            newUserJson.put("lastName", "secondName");
            newUserJson.put("middleName", "middleName");
            newUserJson.put("position", "Сотрудник");
            newUserJson.put("phone", "phone");
            newUserJson.put("isIdentified", true);
            newUserJson.put("docName", "Удостоверение");
            newUserJson.put("docDate", "2018-09-04");
            newUserJson.put("citizenshipCode", "000");
            sendHttpPostRequest("user/save", newUserJson);

            JSONObject jsonFilterByName = new JSONObject();
            jsonFilterByName.put("officeId", 1L);
            jsonFilterByName.put("firstName", "firstName");
            //Получаем список по имени
            HttpResponse getByName = sendHttpPostRequest("user/list", jsonFilterByName);
            List jsonArray2 = getJsonDataArray(getByName);
            Map data = (Map) jsonArray2.get(0);
            String id = (String) data.get("id");

            HttpResponse getById = sendHttpGetRequest("user/" + id, null);

            Map dataById = getJsonData(getById);
            Assert.assertNotNull(dataById);
        }
    }
}
