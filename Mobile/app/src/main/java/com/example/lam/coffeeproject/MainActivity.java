package com.example.lam.coffeeproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
    private final int ERROR_DIALOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ResultReceiver checkLoginResultReceiver = new ResultReceiver(null){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            CheckLoginRequest checkLoginRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            try {
                checkLoginRequest.isLoginSuccess();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

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

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case ERROR_DIALOG:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle("Error");
                dialogBuilder.setMessage("`");

        }
    }
}
