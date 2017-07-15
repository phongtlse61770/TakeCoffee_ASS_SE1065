package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/10/2017.
 */
public class CheckLoginRequest extends BaseRequest{
    private String inputUsername;
    private String inputPassword;

    public CheckLoginRequest(String username, String password) {
        this.inputUsername = username;
        this.inputPassword = password;
    }

    public boolean isLoginSuccess() throws JSONException {
        boolean isSuccess = responseBody.getBoolean("result");
        return isSuccess;
    }

    public boolean isEmployee() throws JSONException {
        boolean isEmployee = responseBody.getBoolean("isEmployee");
        return isEmployee;
    }

    public String getUsername(){
        return inputUsername;
    }


    public void ActiveAllRequestLogin(){
        this.username = inputUsername;
        this.password = inputPassword;
    }

    @Override
    String getUri() {
        String path = "/user/checklogin";
        return ROOT + path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",inputUsername);
        jsonObject.put("password",inputPassword);
        return jsonObject.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.inputUsername);
        dest.writeString(this.inputPassword);
        if(this.responseBody != null){
            dest.writeString(this.responseBody.toString());
        }
    }

    protected CheckLoginRequest(Parcel in) {
        this.inputUsername = in.readString();
        this.inputPassword = in.readString();
        try{
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        }catch (Exception ex){
            //Suppress
        }
    }

    public static final Creator<CheckLoginRequest> CREATOR = new Creator<CheckLoginRequest>() {
        @Override
        public CheckLoginRequest createFromParcel(Parcel source) {
            return new CheckLoginRequest(source);
        }

        @Override
        public CheckLoginRequest[] newArray(int size) {
            return new CheckLoginRequest[size];
        }
    };
}
