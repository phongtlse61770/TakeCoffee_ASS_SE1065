package com.example.lam.coffeeproject;

import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lam.coffeeproject.API.Requests.SignupRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;
import okhttp3.Request;
import org.json.JSONException;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    ResultReceiver resultReceiver = new ResultReceiver(new Handler()){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            SignupRequest signupRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            try {
                Toast.makeText(Signup.this, "done-"+signupRequest.getIsSuccess(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void FinishSignup(View view) {
        EditText usernameTxt = (EditText) findViewById(R.id.txtUsername);
        EditText passwordTxt = (EditText) findViewById(R.id.txtPassword);
        EditText phoneNumberTxt = (EditText) findViewById(R.id.txtPhone);

        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String phonenumber = phoneNumberTxt.getText().toString();

        TakeCoffeeServiceHelper.signup(this,resultReceiver,username,password,phonenumber);
    }

    public void ResetSignupForm(View view) {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        username.setText("");
        EditText password = (EditText) findViewById(R.id.txtPassword);
        password.setText("");
        EditText phoneNumber = (EditText) findViewById(R.id.txtPhone);
        phoneNumber.setText("");
    }
}
