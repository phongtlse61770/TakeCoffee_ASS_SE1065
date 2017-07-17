package com.example.lam.coffeeproject.API;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.example.lam.coffeeproject.API.Requests.*;

/**
 * Created by Phong on 7/5/2017.
 */
public class TakeCoffeeServiceHelper {

    synchronized public static void getMenu(Context context,ResultReceiver resultReceiver){
        GetMenuRequest getMenuRequest = new GetMenuRequest();

        sendRequest(context,getMenuRequest,resultReceiver);
    }

    synchronized public static void signup(Context context,ResultReceiver resultReceiver,String username,String password,String phonenumber){
        SignupRequest signup = new SignupRequest(username, password, phonenumber);

        sendRequest(context,signup,resultReceiver);
    }

    synchronized public static void getShippingFee(Context context,ResultReceiver resultReceiver,double longitude,double latitude){
        GetShippingFeeRequest request = new GetShippingFeeRequest(longitude, latitude);
        sendRequest(context,request,resultReceiver);
    }



    synchronized public static void checkLogin(Context context,ResultReceiver resultReceiver,String username, String password){
        CheckLoginRequest getBalanceRequest = new CheckLoginRequest(username,password);

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
