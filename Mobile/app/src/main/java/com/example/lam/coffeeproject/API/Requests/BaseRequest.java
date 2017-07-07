package com.example.lam.coffeeproject.API.Requests;

import android.support.annotation.RequiresApi;
import okhttp3.*;
import org.json.JSONObject;

/**
 * Created by Phong on 7/7/2017.
 */
public abstract class BaseRequest {
    protected static String ROOT = "http://takecoffeeapi.gear.host/";
    private static MediaType mediaType = MediaType.parse("application/json");
    private String username;
    private String password;

    public BaseRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    abstract String getUri();

    abstract String getJsonBody();

    private void buildUrl(Request.Builder builder) {
        builder.url(getUri());
    }

    private void buildHeader(Request.Builder builder) {
        builder.addHeader("username", username)
                .addHeader("password", password)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache");
    }

    private void buildBody(Request.Builder builder) {
        RequestBody body = RequestBody.create(mediaType, getJsonBody());
        builder.post(body);
    }

    protected Request buildRequest() {
        Request.Builder builder = new Request.Builder();
        this.buildUrl(builder);
        this.buildHeader(builder);
        this.buildBody(builder);
        return builder.build();
    }

    public JSONObject execute() {

    }
}
