package com.example.lam.coffeeproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.lam.coffeeproject.API.Requests.GetBalanceRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;

public class UserAPI extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_api);
    }

    ResultReceiver balanceResultReceiver = new ResultReceiver(null) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            GetBalanceRequest getBalanceRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            Log.e(UserAPI.class.getSimpleName(), "asda");
            try {
                Toast.makeText(UserAPI.this, "done " + getBalanceRequest.getBalance(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(UserAPI.class.getSimpleName(), e.getMessage());
            }
        }
    };


    public void CheckWallet(View view) {
        TakeCoffeeServiceHelper.checkBalance(this, 2, balanceResultReceiver);
    }

    public void ViewStoreLocation(View view) {

    }

    public void ViewMenuToday(View view) {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

}
