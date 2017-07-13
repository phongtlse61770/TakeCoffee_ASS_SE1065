package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Phong on 7/6/2017.
 */
public class GetBalanceRequest extends BaseRequest {
    public double getBalance() throws JSONException, IOException {
        return responseBody.getDouble("balance");
    }

    public GetBalanceRequest() {

    }

    @Override
    String getUri() {
        String path = "user/balance";
        return ROOT+ path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        if(this.responseBody != null){
            dest.writeString(this.responseBody.toString());
        }
    }

    protected GetBalanceRequest(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        try{
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        }catch (Exception ex){
            //Suppress
        }
    }

    public static final Creator<GetBalanceRequest> CREATOR = new Creator<GetBalanceRequest>() {
        @Override
        public GetBalanceRequest createFromParcel(Parcel source) {
            return new GetBalanceRequest(source);
        }

        @Override
        public GetBalanceRequest[] newArray(int size) {
            return new GetBalanceRequest[size];
        }
    };
}
