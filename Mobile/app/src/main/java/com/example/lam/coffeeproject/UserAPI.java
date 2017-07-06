package com.example.lam.coffeeproject;

import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceType;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class UserAPI extends AppCompatActivity {

    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_api);

        IntentFilter filter = new IntentFilter("checkBalance");
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getBundleExtra(TakeCoffeeService.EXTRA_BUNDLE);
                float balance = bundle.getFloat("balance");
                Toast.makeText(UserAPI.this, ""+balance, Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(receiver, filter);
    }

    public void CheckWallet(View view) {
        TakeCoffeeServiceHelper.checkBalance(this);
    }

    public void ViewStoreLocation(View view) {

    }

    public void ViewMenuToday(View view) {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
