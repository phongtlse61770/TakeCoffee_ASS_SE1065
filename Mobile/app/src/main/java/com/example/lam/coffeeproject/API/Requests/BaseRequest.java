package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcelable;
import android.util.Log;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/7/2017.
 */
public abstract class BaseRequest implements Parcelable {
    private static OkHttpClient client = new OkHttpClient();
    protected static String ROOT = "http://takecoffeeapi.gear.host/";
    private MediaType mediaType = MediaType.parse("application/json");
    protected String username;
    protected String password;
    protected JSONObject responseBody;

    abstract String getUri();

    abstract String getRequestJsonBody() throws JSONException;

    private void buildUrl(Request.Builder builder) {
        builder.url(getUri());
    }

    private void buildHeader(Request.Builder builder) throws Exception {
        builder
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache");
        if (username != null && password != null) {
            builder.addHeader("username", username).addHeader("password", password);
        }
    }

    private void buildBody(Request.Builder builder) throws JSONException {
        RequestBody body = RequestBody.create(mediaType, getRequestJsonBody());
        builder.post(body);
    }

    protected Request buildRequest() throws Exception {
        Request.Builder builder = new Request.Builder();
        this.buildUrl(builder);
        this.buildHeader(builder);
        this.buildBody(builder);
        return builder.build();
    }

    public boolean execute() {
        try {
            Request request = buildRequest();
            Response response = client.newCall(request).execute();
            responseBody = new JSONObject(response.body().string());
            return true;
        } catch (Exception ex) {
            Log.e(BaseRequest.class.getSimpleName(), ex.getMessage());
            return false;
        }
    }
}
