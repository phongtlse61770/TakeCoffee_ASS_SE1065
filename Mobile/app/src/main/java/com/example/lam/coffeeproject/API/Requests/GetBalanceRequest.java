package com.example.lam.coffeeproject.API.Requests;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Phong on 7/6/2017.
 */
public class GetBalanceRequest extends BaseRequest{
    private String path = "user/balance";
    public static String REQUEST_NAME = "GET_BALANCE_REQUEST";
    public static String BUNDLE_BALANCE = "BUNDLE_BALANCE";

    public double getBalance() throws JSONException, IOException {
        return responseBody.getDouble("balance");
    }

    private int userId;
    public GetBalanceRequest(int id) {
        super("phongtl", "1234");
        this.userId = id;
    }

    @Override
    String getUri() {
        return ROOT+path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",userId);
        return jsonObject.toString();
    }

}
