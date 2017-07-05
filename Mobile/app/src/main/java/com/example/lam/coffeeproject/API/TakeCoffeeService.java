package com.example.lam.coffeeproject.API;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeService extends IntentService {
    static String SERVICE_NAME = "TakeCoffeeService";

    public static String EXTRA_SERVICE_TYPE = "EXTRA_SERVICE_TYPE";
    public static String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static String EXTRA_BROADCAST_NAME = "EXTRA_BROADCAST_NAME";

    public TakeCoffeeService() {
        super(TakeCoffeeService.SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("TakeCoffeeService","something");
        TakeCoffeeServiceType serviceType = (TakeCoffeeServiceType) intent.getSerializableExtra(TakeCoffeeService.EXTRA_SERVICE_TYPE);
        Bundle dataBundle = intent.getBundleExtra(TakeCoffeeService.EXTRA_BUNDLE);
        String broadcastName = intent.getStringExtra(TakeCoffeeService.EXTRA_BROADCAST_NAME);

        try {
            switch (serviceType) {
                case CheckLogin:

                    break;
                case CheckBalance:
                    getBalance(broadcastName, dataBundle);

                    break;
            }
        } catch (IOException | JSONException e) {
            Log.e(TakeCoffeeService.class.getSimpleName(), e.getMessage());
        }
    }

    private void getBalance(String broadcastName, Bundle dataBundle) throws IOException, JSONException {
        int id = dataBundle.getInt("id");

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"id\": \"2\"\n}");
        Request request = new Request.Builder().url("http://takecoffeeapi.gear.host/user/balance")
                .post(body)
                .addHeader("username", "phongtl")
                .addHeader("password", "1234")
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());

        Float balance = Float.parseFloat(jsonObject.get("balance").toString());
        //---------------------
        Bundle resultBundle = new Bundle();
        resultBundle.putFloat("balance",balance);
        Intent intent = new Intent(broadcastName);
        intent.putExtra(TakeCoffeeService.EXTRA_BUNDLE, resultBundle);
        sendBroadcast(intent);
    }
}
