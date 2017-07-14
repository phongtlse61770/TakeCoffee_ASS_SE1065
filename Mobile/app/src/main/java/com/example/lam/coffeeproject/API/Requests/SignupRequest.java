package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/12/2017.
 */
public class SignupRequest extends BaseRequest {

    private String usernameInput;
    private String passwordInput;
    private String phonenumber;

    public SignupRequest(String username, String password, String phonenumber) {
        this.usernameInput = username;
        this.passwordInput = password;
        this.phonenumber = phonenumber;
    }

    @Override
    String getUri() {
        String path = "/user/create";
        return ROOT + path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",usernameInput);
        jsonObject.put("password",passwordInput);
        jsonObject.put("phonenumber",phonenumber);
        return jsonObject.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.usernameInput);
        dest.writeString(this.passwordInput);
        dest.writeString(this.phonenumber);
        if (this.responseBody != null) {
            dest.writeString(this.responseBody.toString());
        }
    }

    protected SignupRequest(Parcel in) {
        this.usernameInput = in.readString();
        this.passwordInput = in.readString();
        this.phonenumber = in.readString();
        try {
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        } catch (Exception ex) {
            //Suppress
        }
    }

    public static final Creator<SignupRequest> CREATOR = new Creator<SignupRequest>() {
        @Override
        public SignupRequest createFromParcel(Parcel source) {
            return new SignupRequest(source);
        }

        @Override
        public SignupRequest[] newArray(int size) {
            return new SignupRequest[size];
        }
    };
}
