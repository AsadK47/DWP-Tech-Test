package com.mycompany.restservice;

import com.mycompany.myapp.App;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@EnableAutoConfiguration
public class AppController {
    OkHttpClient client = new OkHttpClient();
    Request.Builder builder = new Request.Builder();

    @GetMapping("/")
    public String allUsers() {
        return Objects.requireNonNull(App.retrieveAllUsers(client, builder)).toString();
    }

    @GetMapping("/userid/{id}")
    public String retrieveWithUserId(@PathVariable int id) {
        return Objects.requireNonNull(App.retrieveUserWithId(id, client, builder)).toString();
    }

    @GetMapping("/city/{city}")
    public String retrieveUsersForTheCityOf(@PathVariable String city) {
        return Objects.requireNonNull(App.retrieveUsersForTheCityOf(city, client, builder)).toString();
    }

    @GetMapping("/userswithinfiftymilesoflondon")
    public String retrieveUsersWithinFiftyMilesOfLondon() {
        return App.retrieveUsersWithinFiftyMilesOfLondon(client, builder).toString();
    }
}
