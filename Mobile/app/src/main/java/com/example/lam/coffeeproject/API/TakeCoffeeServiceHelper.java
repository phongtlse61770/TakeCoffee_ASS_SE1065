package com.example.lam.coffeeproject.API;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.example.lam.coffeeproject.API.Requests.GetBalanceRequest;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeServiceHelper {


    synchronized public static void checkLogin(){

    }

    synchronized public static void checkBalance(Context context, int id, ResultReceiver resultReceiver){

        GetBalanceRequest getBalanceRequest = new GetBalanceRequest(id);

        Intent intent = new Intent(context,TakeCoffeeService.class);
        intent.putExtra(TakeCoffeeService.EXTRA_REQUEST,getBalanceRequest);
        intent.putExtra(TakeCoffeeService.EXTRA_CALLBACK,resultReceiver);

        context.startService(intent);
    }
}
