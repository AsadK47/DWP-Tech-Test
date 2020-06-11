package com.mycompany.variableconfig;

public final class VariableConfig {
    private VariableConfig() {

    }

    public static final int USER_ID = 1;
    public static final int NUMBER_OF_USERS_PLUS_ONE = 1001;
    public static final int MAX_LONDON_LATITUDE = 52;
    public static final int MIN_LONDON_LATITUDE = 50;
    public static final int MAX_LONDON_LONGITUDE = 2;
    public static final int MIN_LONDON_LONGITUDE = -1;
    public static final String URL_USER_FOR_ID = "https://bpdts-test-app.herokuapp.com/user/%s";
    public static final String URL_USERS_FOR_CITY_OF = "https://bpdts-test-app.herokuapp.com/city/%s/users";
    public static final String FIFTY_MILE_MESSAGE = "All users with co-ordinates within 50 miles of London are: ";
    public static final String USERS_FOR_CITY_MESSAGE = "All users with their city as London are: ";
    public static final String USER_WITH_ID_MESSAGE = "User with user id " + USER_ID + ": ";
    public static final String LONDON = "London";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String ID = "id";
    public static final String CITY = "city";
}
