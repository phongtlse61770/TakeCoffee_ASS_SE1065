package com.example.lam.coffeeproject.API.Requests;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Phong on 7/7/2017.
 */
public abstract class BaseRequest {
    protected static OkHttpClient client = new OkHttpClient();
    protected static String ROOT = "http://takecoffeeapi.gear.host/";
    private static MediaType mediaType = MediaType.parse("application/json");
    private String username;
    private String password;

    public BaseRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    abstract String getUri();

    abstract String getRequestJsonBody() throws JSONException;

    private void buildUrl(Request.Builder builder) {
        builder.url(getUri());
    }

    private void buildHeader(Request.Builder builder) {
        builder.addHeader("username", username)
                .addHeader("password", password)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache");
    }

    private void buildBody(Request.Builder builder) throws JSONException {
        RequestBody body = RequestBody.create(mediaType, getRequestJsonBody());
        builder.post(body);
    }

    protected Request buildRequest() throws JSONException {
        Request.Builder builder = new Request.Builder();
        this.buildUrl(builder);
        this.buildHeader(builder);
        this.buildBody(builder);
        return builder.build();
    }

    public JSONObject execute() throws IOException, JSONException {
        Request request = buildRequest();
        Response response = client.newCall(request).execute();
        return new JSONObject(response.body().string());
    }
}
