import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println(retrieveUserId(1));
        retrieveLondonUsers();
    }

    public static JSONObject retrieveUserId(int id) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://bpdts-test-app.herokuapp.com/user/%s", id))
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string());
        } catch (JSONException | IOException exception) {
            System.out.println(exception);
        }
        return null;
    }

    public static JSONArray retrieveLondonUsers() {
        OkHttpClient client = new OkHttpClient();
        JSONArray jsonArray = new JSONArray();

        Request.Builder builder = new Request.Builder();
        for (int i = 1; i < 1001; i++) {
            Request request =
                    builder.url(String.format("https://bpdts-test-app.herokuapp.com/user/%s", i))
                            .get()
                            .build();

            try (Response response = client.newCall(request).execute()) {
                JSONObject jsonObject = new JSONObject(response.body().string());
                if ("london".equalsIgnoreCase(jsonObject.getString("city"))) {
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException | IOException exception) {
                System.out.println(exception);
            }
        }

        return jsonArray;
    }
}
