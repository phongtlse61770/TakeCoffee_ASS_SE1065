package com.example.lam.coffeeproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.lam.coffeeproject.API.Requests.SignupRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;
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
                String message;
                if(signupRequest.getIsSuccess()){
                    message = "Signup success";
                }else{
                    message = "Signup fail";
                }
                Signup.this.showMessageDialog(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void FinishSignup(View view) {
        EditText usernameTxt = (EditText) findViewById(R.id.txtUsername);
        EditText passwordTxt = (EditText) findViewById(R.id.txtPassword);
        EditText confirmPasswordTxt = (EditText) findViewById(R.id.txtConfirmPassword);
        EditText phoneNumberTxt = (EditText) findViewById(R.id.txtPhone);

        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String confirmPassword = confirmPasswordTxt.getText().toString();
        String phonenumber = phoneNumberTxt.getText().toString();

        if(password.equals(confirmPassword)){
            TakeCoffeeServiceHelper.signup(this,resultReceiver,username,password,phonenumber);
        }else{
            this.showMessageDialog("Your confirm password not match");
        }

    }

    public void ResetSignupForm(View view) {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        username.setText("");
        EditText password = (EditText) findViewById(R.id.txtPassword);
        password.setText("");
        EditText confirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        confirmPassword.setText("");
        EditText phoneNumber = (EditText) findViewById(R.id.txtPhone);
        phoneNumber.setText("");
    }

    private void showMessageDialog(String message){
        AlertDialog.Builder balance = new AlertDialog.Builder(Signup.this);
        balance.setMessage(message);
        balance.setCancelable(true);
        balance.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = balance.create();
        dialog.show();
    }
}
