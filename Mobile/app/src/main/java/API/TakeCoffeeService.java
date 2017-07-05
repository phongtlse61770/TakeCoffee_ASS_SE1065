package API;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Phong on 7/5/2017.
 */
class TakeCoffeeService extends IntentService {
    static String SERVICE_NAME = "TakeCoffeeService";
    static String EXTRA_SERVICE_TYPE = "EXTRA_SERVICE_TYPE";
    static String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    static String EXTRA_BROADCAST_NAME = "EXTRA_BROADCAST_NAME";

    public TakeCoffeeService() {
        super(TakeCoffeeService.SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TakeCoffeeServiceType serviceType = (TakeCoffeeServiceType) intent.getSerializableExtra(TakeCoffeeService.EXTRA_SERVICE_TYPE);
        Bundle dataBundle = intent.getBundleExtra(TakeCoffeeService.EXTRA_BUNDLE);
        String broadcastName = intent.getStringExtra(TakeCoffeeService.EXTRA_BROADCAST_NAME);
    }
}
