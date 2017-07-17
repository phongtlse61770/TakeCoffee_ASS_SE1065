package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/17/2017.
 */
public class GetShippingFeeRequest extends BaseRequest {
    public double longitude;
    public double latitude;

    public GetShippingFeeRequest(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getFee() throws JSONException {
        return responseBody.getDouble("fee");
    }

    @Override
    String getUri() {
        String path = "/utility/shipfee";
        return ROOT + path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Longitude",longitude);
        jsonObject.put("Latitude",latitude);
        return jsonObject.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        if (this.responseBody != null) {
            dest.writeString(this.responseBody.toString());
        }
    }

    protected GetShippingFeeRequest(Parcel in) {
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        try {
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        } catch (Exception ex) {
            //Suppress
        }
    }

    public static final Creator<GetShippingFeeRequest> CREATOR = new Creator<GetShippingFeeRequest>() {
        @Override
        public GetShippingFeeRequest createFromParcel(Parcel source) {
            return new GetShippingFeeRequest(source);
        }

        @Override
        public GetShippingFeeRequest[] newArray(int size) {
            return new GetShippingFeeRequest[size];
        }
    };
}
