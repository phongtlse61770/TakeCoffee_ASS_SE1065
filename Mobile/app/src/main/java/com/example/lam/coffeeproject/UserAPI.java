package com.example.lam.coffeeproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserAPI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_api);
    }

    public void CheckWallet(View view) {

    }

    public void ViewStoreLocation(View view) {

    }

    public void ViewMenuToday(View view) {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
}
