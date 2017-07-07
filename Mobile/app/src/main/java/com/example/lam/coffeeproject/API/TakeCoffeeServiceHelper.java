package com.example.lam.coffeeproject.API;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeServiceHelper {


    synchronized public static void checkLogin(){

    }

    synchronized public static void checkBalance(Context context){

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id",2);

        Intent intent = new Intent(context,TakeCoffeeService.class);
        intent.putExtra(TakeCoffeeService.EXTRA_SERVICE_TYPE,TakeCoffeeServiceType.CheckBalance);
//        intent.putExtra(TakeCoffeeService.EXTRA_BROADCAST_NAME,broadcastName);
        intent.putExtra(TakeCoffeeService.EXTRA_BUNDLE,dataBundle);

        context.startService(intent);
    }
}
