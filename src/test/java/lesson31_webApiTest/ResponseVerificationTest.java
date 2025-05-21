package lesson31_webApiTest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseVerificationTest {

    @Test
    public void testGetCarBrandsResponse() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://qauto.forstudy.space/api/cars/brands"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        softAssert.assertEquals(statusCode, 200, "Expected status code 200");

        JSONObject responseBody = new JSONObject(response.body());
        JSONArray brands = responseBody.getJSONArray("data");

        boolean containsAudi = false;
        for (int i = 0; i < brands.length(); i++) {
            JSONObject brand = brands.getJSONObject(i);
            if (brand.getInt("id") == 1 && "Audi".equals(brand.getString("title"))) {
                containsAudi = true;
                break;
            }
        }

        softAssert.assertTrue(containsAudi, "Response body should contain brand with id=1 and title='Audi'");
        softAssert.assertAll();
    }
}
