package com.example.lam.coffeeproject.API;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.example.lam.coffeeproject.API.Requests.BaseRequest;
import com.example.lam.coffeeproject.API.Requests.CheckLoginRequest;
import com.example.lam.coffeeproject.API.Requests.GetBalanceRequest;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeServiceHelper {


    synchronized public static void checkLogin(Context context,ResultReceiver resultReceiver,String username, String pasword){
        CheckLoginRequest getBalanceRequest = new CheckLoginRequest(username,pasword);

        sendRequest(context,getBalanceRequest,resultReceiver);
    }

    synchronized public static void checkBalance(Context context,ResultReceiver resultReceiver){
        GetBalanceRequest getBalanceRequest = new GetBalanceRequest();

        sendRequest(context,getBalanceRequest,resultReceiver);
    }

    synchronized public static void sendRequest(Context context, BaseRequest request, ResultReceiver resultReceiver){
        Intent intent = new Intent(context,TakeCoffeeService.class);
        intent.putExtra(TakeCoffeeService.EXTRA_REQUEST,request);
        intent.putExtra(TakeCoffeeService.EXTRA_CALLBACK,resultReceiver);

        context.startService(intent);
    }
}
