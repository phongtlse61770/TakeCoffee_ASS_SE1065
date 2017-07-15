package com.example.lam.coffeeproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.EditText;
import com.example.lam.coffeeproject.API.Requests.CheckLoginRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean error = getIntent().getBooleanExtra("error", false);
        if (error) {
            loginFailHandler();
        }
    }

    private void loginFailHandler(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Login fail");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void Login(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.txtSignupUsername);
        EditText passwordEditText = (EditText) findViewById(R.id.txtSignupPassword);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, LoadingScreenActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void Signup(View view) {
        Intent i = new Intent(this,Signup.class);
        startActivity(i);
    }

}
