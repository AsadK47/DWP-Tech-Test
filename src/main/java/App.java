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
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        System.out.println(retrieveUserId(1, client, builder));
        System.out.println(retrieveUsersForTheCityOf(LONDON, client, builder));
        System.out.println(retrieveUsersWithinFiftyMilesOfLondon(client, builder));
    }

    public static JSONObject retrieveUserId(int id, OkHttpClient client, Request.Builder builder) {
        try {
            return new JSONObject(BuildRequest(USER_FOR_ID, String.valueOf(id), client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersForTheCityOf(String city, OkHttpClient client, Request.Builder builder) {
        String capitalisedCity = city.substring(0, 1).toUpperCase() + city.substring(1);

        try {
            return new JSONArray(BuildRequest(USERS_FOR_CITY_OF, capitalisedCity, client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersWithinFiftyMilesOfLondon(OkHttpClient client, Request.Builder builder) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < 1001; i++) {
            try {
                JSONObject jsonObject = new JSONObject(BuildRequest(USER_FOR_ID, String.valueOf(266), client, builder));
                double latitude = Double.parseDouble(jsonObject.getString("latitude"));
                double longitude = Double.parseDouble(jsonObject.getString("longitude"));
                if ((latitude < 52 && latitude > 50) && (longitude < 2 && longitude > -1)) {
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }

        return jsonArray;
    }

    public static JSONArray retrieveLondonUsersWithForLoop(OkHttpClient client, Request.Builder builder) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < 1001; i++) {
            try {
                JSONObject jsonObject = new JSONObject(BuildRequest(USER_FOR_ID, String.valueOf(i), client, builder));
                if ("london".equalsIgnoreCase(jsonObject.getString("city"))) {
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }

        return jsonArray;
    }

    private static String BuildRequest(String url, String argument, OkHttpClient okHttpClient, Request.Builder builder) {
        Request request = builder.url(String.format(url, argument))
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
