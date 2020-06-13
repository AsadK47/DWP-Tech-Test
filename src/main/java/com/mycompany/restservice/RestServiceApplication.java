package com.mycompany.restservice;

import com.mycompany.myapp.App;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

//	@Bean
//	public JSONObject j() {
//		OkHttpClient okHttpClient = new OkHttpClient();
//		Request.Builder builder = new Request.Builder();
//
//		System.out.println("Factory method called...");
//		return App.retrieveUserWithId(1, okHttpClient, builder);
//	}

}
