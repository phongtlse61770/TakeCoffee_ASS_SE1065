package com.example.lam.coffeeproject.API;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.example.lam.coffeeproject.API.Requests.BaseRequest;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeService extends IntentService {
    static String SERVICE_NAME = "TakeCoffeeService";

    public static String EXTRA_REQUEST = "EXTRA_REQUEST";
    public static String EXTRA_CALLBACK = "EXTRA_CALLBACK";

    public TakeCoffeeService() {
        super(TakeCoffeeService.SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        BaseRequest request = intent.getParcelableExtra(TakeCoffeeService.EXTRA_REQUEST);
        ResultReceiver callback = intent.getParcelableExtra(TakeCoffeeService.EXTRA_CALLBACK);

        if (request.execute()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_REQUEST,request);
            callback.send(0,bundle);
        }
    }
}
