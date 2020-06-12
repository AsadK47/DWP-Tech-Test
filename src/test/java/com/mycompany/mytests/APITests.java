package com.mycompany.mytests;

import com.mycompany.myapp.App;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

import static com.mycompany.variableconfig.VariableConfig.*;

public class APITests {
    OkHttpClient client = new OkHttpClient();
    Request.Builder builder = new Request.Builder();

    @Test
    public void verifyUser1() throws JSONException {
        JSONObject constructedUserAsObject = constructUser1();
        JSONObject retrievedUserAsObject = App.retrieveUserWithId(1, client, builder);
        assert retrievedUserAsObject != null;

        Assert.assertEquals(1, retrievedUserAsObject.get(ID));
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
        JSONArray londonUsers = App.retrieveUsersForTheCityOf(LONDON, client, builder);
        int expectedNumberOfUsers = 6;

        assert londonUsers != null;
        assertEquals(expectedNumberOfUsers, londonUsers.length());
    }

    @Test
    public void retrieveUsersWithin50MilesOfLondon() {
        JSONArray usersWithinFiftyMiles = App.retrieveUsersWithinFiftyMilesOfLondon(client, builder);

        for (int i = 0; i < usersWithinFiftyMiles.length(); i++) {
            try {
                assertThat(usersWithinFiftyMiles.getJSONObject(i).getInt(LATITUDE),
                        allOf(greaterThan(MIN_LONDON_LATITUDE), lessThan(MAX_LONDON_LATITUDE)));

                assertThat(usersWithinFiftyMiles.getJSONObject(i).getInt(LONGITUDE),
                        allOf(greaterThan(MIN_LONDON_LONGITUDE), lessThan(MAX_LONDON_LONGITUDE)));

            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
    }
}
