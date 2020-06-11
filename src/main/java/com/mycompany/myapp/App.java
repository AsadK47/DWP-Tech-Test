package com.mycompany.myapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import static com.mycompany.variableconfig.VariableConfig.*;

public class App {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        System.out.println(USER_WITH_ID_MESSAGE + retrieveUserWithId(USER_ID, client, builder));
        System.out.println(USERS_FOR_CITY_MESSAGE + retrieveUsersForTheCityOf((LONDON), client, builder));
        System.out.println(FIFTY_MILE_MESSAGE + retrieveUsersWithinFiftyMilesOfLondon(client, builder));
    }

    public static JSONObject retrieveUserWithId(int id, OkHttpClient client, Request.Builder builder) {
        try {
            return new JSONObject(BuildRequest(URL_USER_FOR_ID, String.valueOf(id), client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersForTheCityOf(String city, OkHttpClient client, Request.Builder builder) {
        String capitalisedCity = city.substring(0, 1).toUpperCase() + city.substring(1);

        try {
            return new JSONArray(BuildRequest(URL_USERS_FOR_CITY_OF, capitalisedCity, client, builder));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static JSONArray retrieveUsersWithinFiftyMilesOfLondon(OkHttpClient client, Request.Builder builder) {
        JSONArray jsonArray = new JSONArray();
        for (int userId = 1; userId < NUMBER_OF_USERS_PLUS_ONE; userId++) {
            try {
                JSONObject user = new JSONObject(BuildRequest(URL_USER_FOR_ID, String.valueOf(userId), client, builder));
                double latitude = returnAsDecimal(user.getString(LATITUDE));
                double longitude = returnAsDecimal(user.getString(LONGITUDE));
                if ((latitude < MAX_LONDON_LATITUDE && latitude > MIN_LONDON_LATITUDE)
                        && (longitude < MAX_LONDON_LONGITUDE && longitude > MIN_LONDON_LONGITUDE)) {
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
                JSONObject jsonObject = new JSONObject(BuildRequest(URL_USER_FOR_ID, String.valueOf(i), client, builder));
                if (LONDON.equalsIgnoreCase(jsonObject.getString(CITY))) {
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
