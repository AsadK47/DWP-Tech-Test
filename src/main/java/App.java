import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println(retrieveUserId("1"));
        System.out.println(retrieveUsersForTheCityOf("london"));
    }

    public static JSONObject retrieveUserId(String id) {
        try {
            return new JSONObject(BuildRequest("https://bpdts-test-app.herokuapp.com/user/%s", id));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersForTheCityOf(String city) {
        String capitalisedCity = city.substring(0, 1).toUpperCase() + city.substring(1);
        try {
            return new JSONArray(BuildRequest("https://bpdts-test-app.herokuapp.com/city/%s/users", capitalisedCity));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveLondonUsersWithForLoop() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < 1001; i++) {
            try {
                JSONObject jsonObject = new JSONObject(BuildRequest("https://bpdts-test-app.herokuapp.com/user/%s", String.valueOf(i)));
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
            return response.body().string();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
