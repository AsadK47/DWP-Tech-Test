import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class APITests {
    @Test
    public void verifyUser1() throws JSONException {
        JSONObject constructedUserAsObject = constructUser1();
        JSONObject retrievedUserAsObject = App.retrieveUserId(1);
        assert retrievedUserAsObject != null;

        Assert.assertEquals(1, retrievedUserAsObject.get("id"));
        JSONAssert.assertEquals(constructedUserAsObject, retrievedUserAsObject, true);
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
    public void retrieveUsersForTheCityOfLondon() {
        JSONArray londonUsers = App.retrieveUsersForTheCityOf(App.LONDON);
        int expectedNumberOfUsers = 6;

        assert londonUsers != null;
        assertEquals(expectedNumberOfUsers, londonUsers.length());
    }

    @Test
    public void retrieveUsersWithin50MilesOfLondon() {
        JSONArray usersWithinFiftyMiles = App.retrieveUsersWithinFiftyMilesOfLondon();
        assert usersWithinFiftyMiles != null;

        for (int i = 0; i < usersWithinFiftyMiles.length(); i++) {
            try {
                assertThat(usersWithinFiftyMiles.getJSONObject(i).getInt("latitude"), allOf(greaterThan(50), lessThan(52)));
                assertThat(usersWithinFiftyMiles.getJSONObject(i).getInt("longitude"), allOf(greaterThan(-1), lessThan(2)));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
    }

    //    @Test
    //    public void retrieveLondonUsersWithLoop() throws JSONException {
    //        JSONArray londonUsers = App.retrieveLondonUsersWithForLoop();
    //        for (int i = 0; i < londonUsers.length(); i++) {
    //            assertEquals("London", londonUsers.getJSONObject(i).get("city"));
    //        }
    //    }
}
