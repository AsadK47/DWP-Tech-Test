import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class App extends VariableConfig {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        System.out.println(retrieveUserId(1, client, builder));
        System.out.println(retrieveUsersForTheCityOf(getLONDON(), client, builder));
        System.out.println(getFiftyMileMessage() + retrieveUsersWithinFiftyMilesOfLondon(client, builder));
    }

    public static JSONObject retrieveUserId(int id, OkHttpClient client, Request.Builder builder) {
        try {
            return new JSONObject(BuildRequest(getUrlUserForId(), String.valueOf(id), client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersForTheCityOf(String city, OkHttpClient client, Request.Builder builder) {
        String capitalisedCity = city.substring(0, 1).toUpperCase() + city.substring(1);

        try {
            return new JSONArray(BuildRequest(getUrlUsersForCityOf(), capitalisedCity, client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersWithinFiftyMilesOfLondon(OkHttpClient client, Request.Builder builder) {
        JSONArray jsonArray = new JSONArray();
        for (int userId = 1; userId < getNumberOfUsersPlusOne(); userId++) {
            try {
                JSONObject user = new JSONObject(BuildRequest(getUrlUserForId(), String.valueOf(userId), client, builder));
                double latitude = returnAsDecimal(user.getString(getLATITUDE()));
                double longitude = returnAsDecimal(user.getString(getLONGITUDE()));
                if ((latitude < getMaxLondonLatitude() && latitude > getMinLondonLatitude())
                        && (longitude < getMaxLondonLongitude() && longitude > getMinLondonLongitude())) {
                    jsonArray.put(user);
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
                JSONObject jsonObject = new JSONObject(BuildRequest(getUrlUserForId(), String.valueOf(i), client, builder));
                if (getLONDON().equalsIgnoreCase(jsonObject.getString("city"))) {
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

    private static Double returnAsDecimal(String string) {
        return Double.parseDouble(string);
    }
}
