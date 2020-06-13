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
    OkHttpClient okHttpClient = new OkHttpClient();
    Request.Builder builder = new Request.Builder();

    @GetMapping("/userid/{id}")
    public String retrieveWithUserId(@PathVariable int id) {
        return Objects.requireNonNull(App.retrieveUserWithId(id, okHttpClient, builder)).toString();
    }

    @GetMapping("/city/{city}")
    public String retrieveUsersForTheCityOf(@PathVariable String city) {
        return Objects.requireNonNull(App.retrieveUsersForTheCityOf(city, okHttpClient, builder)).toString();
    }

    @GetMapping("/userswithinfiftymilesoflondon")
    public String retrieveUsersWithinFiftyMilesOfLondon() {
        return App.retrieveUsersWithinFiftyMilesOfLondon(okHttpClient, builder).toString();
    }
}
