package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import okhttp3.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/14/2017.
 */
public class GetMenuRequest extends BaseRequest{
    public GetMenuRequest() {

    }

    @Override
    String getUri() {
        String path = "/product/menu";
        return ROOT + path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(this.responseBody != null){
            dest.writeString(this.responseBody.toString());
        }
    }

    protected GetMenuRequest(Parcel in) {
        try{
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        }catch (Exception ex){
            //Suppress
        }
    }

    public static final Creator<GetMenuRequest> CREATOR = new Creator<GetMenuRequest>() {
        @Override
        public GetMenuRequest createFromParcel(Parcel source) {
            return new GetMenuRequest(source);
        }

        @Override
        public GetMenuRequest[] newArray(int size) {
            return new GetMenuRequest[size];
        }
    };
}
