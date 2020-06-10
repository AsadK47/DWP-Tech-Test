import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class App {
    private static final String USER_FOR_ID = "https://bpdts-test-app.herokuapp.com/user/%s";
    private static final String USERS_FOR_CITY_OF = "https://bpdts-test-app.herokuapp.com/city/%s/users";
    public static final String LONDON = "London";

    public static void main(String[] args) {
        System.out.println(retrieveUserId(1));
        System.out.println(retrieveUsersForTheCityOf(LONDON));
    }

    public static JSONObject retrieveUserId(int id) {
        try {
            return new JSONObject(BuildRequest(USER_FOR_ID, String.valueOf(id)));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersForTheCityOf(String city) {
        String capitalisedCity = city.substring(0, 1).toUpperCase() + city.substring(1);

        try {
            return new JSONArray(BuildRequest(USERS_FOR_CITY_OF, capitalisedCity));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersWithinFiftyMilesOfLondon() {
        return null;
    }

    public static JSONArray retrieveLondonUsersWithForLoop() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < 1001; i++) {
            try {
                JSONObject jsonObject = new JSONObject(BuildRequest(USER_FOR_ID, String.valueOf(i)));
                if ("london".equalsIgnoreCase(jsonObject.getString("city"))) {
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }

        return jsonArray;
    }

    private static String BuildRequest(String url, String argument) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(url, argument))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Is response successful: " + response.isSuccessful());
            return response.body().string();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
