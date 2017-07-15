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
        Intent i = new Intent(MainActivity.this,UserAPI.class);
        i.putExtra("username",checkLoginRequest.getUsername());
        startActivity(i);
    }

    private void loginFailHandler(CheckLoginRequest checkLoginRequest){
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
        EditText usernameEditText = (EditText) findViewById(R.id.editText);
        EditText passwordEditText = (EditText) findViewById(R.id.editText3);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        TakeCoffeeServiceHelper.checkLogin(this,checkLoginResultReceiver,username,password);
    }

    public void Signup(View view) {
        Intent i = new Intent(this,Signup.class);
        startActivity(i);
    }

}
