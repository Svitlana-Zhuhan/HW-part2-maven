package lesson30_restApiTest;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogoutResponseTest {

    @Test
    public void testLogoutResponse() {
        SoftAssert softAssert = new SoftAssert();
        try {
            URL url = new URL("https://qauto.forstudy.space/api/auth/logout");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            softAssert.assertEquals(responseCode, 200, "Expected status-code 200");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseBody.append(line);
            }
            in.close();

            String expectedBody = "{\"status\":\"ok\"}";
            softAssert.assertEquals(responseBody.toString(), expectedBody, "Response body not equal");
        } catch (Exception e) {
            e.printStackTrace();
            softAssert.fail("Exception during request: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}
