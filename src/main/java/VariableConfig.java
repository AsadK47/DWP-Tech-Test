public class VariableConfig {
    private static final String URL_USER_FOR_ID = "https://bpdts-test-app.herokuapp.com/user/%s";
    private static final String URL_USERS_FOR_CITY_OF = "https://bpdts-test-app.herokuapp.com/city/%s/users";
    private static final String LONDON = "London";
    private static final int NUMBER_OF_USERS_PLUS_ONE = 1001;
    private static final int MAX_LONDON_LATITUDE = 52;
    private static final int MIN_LONDON_LATITUDE = 50;
    private static final int MAX_LONDON_LONGITUDE = 2;
    private static final int MIN_LONDON_LONGITUDE = -1;

    public static String getUrlUserForId() {
        return URL_USER_FOR_ID;
    }

    public static String getUrlUsersForCityOf() {
        return URL_USERS_FOR_CITY_OF;
    }

    public static String getLONDON() {
        return LONDON;
    }

    public static int getNumberOfUsersPlusOne() {
        return NUMBER_OF_USERS_PLUS_ONE;
    }

    public static int getMaxLondonLatitude() {
        return MAX_LONDON_LATITUDE;
    }

    public static int getMinLondonLatitude() {
        return MIN_LONDON_LATITUDE;
    }

    public static int getMaxLondonLongitude() {
        return MAX_LONDON_LONGITUDE;
    }

    public static int getMinLondonLongitude() {
        return MIN_LONDON_LONGITUDE;
    }
}
