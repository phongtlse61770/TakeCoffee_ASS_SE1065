
package com.example.lam.coffeeproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lam.coffeeproject.API.Requests.CheckLoginRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;

import org.json.JSONException;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);

        final String username = getIntent().getStringExtra("username");
        final String password = getIntent().getStringExtra("password");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TakeCoffeeServiceHelper.checkLogin(LoadingScreenActivity.this
                        , checkLoginResultReceiver, username, password);
            }
        }, 0);


    }

    ResultReceiver checkLoginResultReceiver = new ResultReceiver(new Handler()){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            CheckLoginRequest checkLoginRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);

            try {
                boolean isLoginValid = checkLoginRequest.isLoginSuccess();
                if(isLoginValid){
                    loginSuccessHandler(checkLoginRequest);
                }else{
                    loginFailHandler(checkLoginRequest);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void loginSuccessHandler(CheckLoginRequest checkLoginRequest){
        checkLoginRequest.ActiveAllRequestLogin();
        Intent i = new Intent(LoadingScreenActivity.this,UserAPI.class);
        i.putExtra("username",checkLoginRequest.getUsername());
        startActivity(i);
    }

    private void loginFailHandler(CheckLoginRequest checkLoginRequest){
        Intent i = new Intent(LoadingScreenActivity.this, MainActivity.class);
        i.putExtra("error", true);
        startActivity(i);
    }
}
