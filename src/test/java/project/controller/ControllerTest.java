package project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import project.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ControllerTest {

    protected final String BASE_URL = "http://localhost:8888/";

    protected boolean checkApplicationEnable() {
        HttpResponse response = sendHttpGetRequest("ping", null);
        if (response != null) {
            return response.getStatusLine().getStatusCode() == 200;
        }
        return false;
    }

    protected HttpResponse sendHttpPostRequest(String url, JSONObject requestJson) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httpPost = new HttpPost(BASE_URL + url);
            if (requestJson != null) {
                StringEntity params = new StringEntity(requestJson.toString());
                params.setContentType("application/json");
                httpPost.setEntity(params);
            }
            httpPost.addHeader("content-type", "application/json");

            return httpClient.execute(httpPost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected HttpResponse sendHttpGetRequest(String url, HttpParams params) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpGet = new HttpGet(BASE_URL + url);
            if (params != null) {
                httpGet.setParams(params);
            }
            httpGet.addHeader("content-type", "application/json");
            return httpClient.execute(httpGet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected List getJsonDataArray(HttpResponse response) {
        List data = new ArrayList();
        if (response != null) {
            JSONObject jsonObject = getJSONObjectFromHttpResponse(response);
            if (jsonObject.get("data") != null) {
                data = (ArrayList) jsonObject.get("data");
            }
        }
        return data;
    }


    protected Map getJsonData(HttpResponse response) {
        Map data = new HashMap();
        if (response != null) {
            JSONObject jsonObject = getJSONObjectFromHttpResponse(response);
            if (jsonObject.get("data") != null) {
                data = (LinkedTreeMap) jsonObject.get("data");
            }
        }
        return data;
    }

    protected JSONObject getJSONObjectFromHttpResponse(HttpResponse response) {
        if (response != null) {
            try {
                GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
                Gson gson = builder.create();
                String json_string = EntityUtils.toString(response.getEntity());
                json_string = json_string.substring(1, json_string.length() - 1);
                json_string = json_string.replaceAll("\\\\", "");
                JSONObject jsonObject = gson.fromJson(json_string, JSONObject.class);
                return jsonObject;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
