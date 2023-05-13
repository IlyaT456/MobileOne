package api;

import config.MobileConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import org.aeonbits.owner.ConfigFactory;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        //MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        MobileConfig config = ConfigFactory.create(MobileConfig.class);
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .filter(new AllureRestAssured())
                .auth().basic(config.getLogin(), config.getPassword())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
