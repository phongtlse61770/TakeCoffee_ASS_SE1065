package com.example.lam.coffeeproject.API.Requests;

import android.os.Parcel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phong on 7/17/2017.
 */
public class OrderRequest extends BaseRequest {
    public Map<Integer, Integer> orderDetail;
    public double shipFee;

    public OrderRequest(Map<Integer, Integer> orderDetail, double shipFee) {
        this.orderDetail = orderDetail;
        this.shipFee = shipFee;
    }

    public boolean isSuccess() throws JSONException {
        return responseBody.getBoolean("result");
    }

    @Override
    String getUri() {
        String path = "/order/create";
        return ROOT + path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shipfee",shipFee);
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Integer, Integer> entry :
                orderDetail.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            JSONObject orderDetail = new JSONObject();
            orderDetail.put("id",productId);
            orderDetail.put("quantity",quantity);
            jsonArray.put(orderDetail);
        }
        jsonObject.put("order_detail",jsonArray);
        return jsonObject.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderDetail.size());
        for (Map.Entry<Integer, Integer> entry : this.orderDetail.entrySet()) {
            dest.writeValue(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeDouble(this.shipFee);
        if (this.responseBody != null) {
            dest.writeString(this.responseBody.toString());
        }
    }

    protected OrderRequest(Parcel in) {
        int orderDetailSize = in.readInt();
        this.orderDetail = new HashMap<Integer, Integer>(orderDetailSize);
        for (int i = 0; i < orderDetailSize; i++) {
            Integer key = (Integer) in.readValue(Integer.class.getClassLoader());
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.orderDetail.put(key, value);
        }
        this.shipFee = in.readDouble();
        try {
            this.responseBody = in.readParcelable(JSONObject.class.getClassLoader());
        } catch (Exception ex) {
            //Suppress
        }
    }

    public static final Creator<OrderRequest> CREATOR = new Creator<OrderRequest>() {
        @Override
        public OrderRequest createFromParcel(Parcel source) {
            return new OrderRequest(source);
        }

        @Override
        public OrderRequest[] newArray(int size) {
            return new OrderRequest[size];
        }
    };
}
