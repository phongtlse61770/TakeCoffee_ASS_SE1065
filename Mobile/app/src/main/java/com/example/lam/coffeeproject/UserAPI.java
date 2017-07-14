package com.example.lam.coffeeproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.lam.coffeeproject.API.Requests.GetBalanceRequest;
import com.example.lam.coffeeproject.API.Requests.GetMenuRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;

public class UserAPI extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra("username");
        setContentView(R.layout.activity_user_api);
    }

    ResultReceiver getMenuReceiver = new ResultReceiver(new Handler()) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            GetMenuRequest getMenuRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            try {
                Toast.makeText(UserAPI.this, "done", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(UserAPI.class.getSimpleName(), e.getMessage());
            }
        }
    };


    ResultReceiver balanceResultReceiver = new ResultReceiver(new Handler()) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            GetBalanceRequest getBalanceRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            try {
                Toast.makeText(UserAPI.this, "done " + getBalanceRequest.getBalance(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(UserAPI.class.getSimpleName(), e.getMessage());
            }
        }
    };


    public void CheckWallet(View view) {
        TakeCoffeeServiceHelper.checkBalance(this, balanceResultReceiver);
    }

    public void ViewStoreLocation(View view) {

    }

    public void ViewMenuToday(View view) {
        TakeCoffeeServiceHelper.getMenu(this,getMenuReceiver);
//        Intent i = new Intent(this, Menu.class);
//        startActivity(i);
    }

}
