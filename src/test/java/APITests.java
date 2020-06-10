import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class APITests {
    @Test
    public void verifyUser1() throws JSONException {
        JSONObject user1 = constructUser1();
        JSONAssert.assertEquals(user1, App.retrieveUserId(1), true);
    }

    private JSONObject constructUser1() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("first_name", "Maurise");
        jsonObject.put("last_name", "Shieldon");
        jsonObject.put("email", "mshieldon0@squidoo.com");
        jsonObject.put("ip_address", "192.57.232.111");
        jsonObject.put("latitude", 34.003135);
        jsonObject.put("longitude", -117.7228641);
        jsonObject.put("city", "Kax");

        return jsonObject;
    }

    @Test
    public void retrieveUsersWithCityLondon() throws JSONException {
        JSONArray londonUsers = App.retrieveLondonUsers();
        for (int i = 0; i < londonUsers.length(); i++) {
            assertEquals("London", londonUsers.getJSONObject(i).get("city"));
        }
    }
}
