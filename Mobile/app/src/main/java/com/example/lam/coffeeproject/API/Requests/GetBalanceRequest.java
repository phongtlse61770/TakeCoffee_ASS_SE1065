package com.example.lam.coffeeproject.API.Requests;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/6/2017.
 */
public class GetBalanceRequest extends BaseRequest{
    private String path = "user/balance";
    public static String REQUEST_NAME = "GET_BALANCE_REQUEST";
    public static String BUNDLE_BALANCE = "BALANCE";

    public GetBalanceRequest() {
        super("phongtl", "1234");
    }

    @Override
    String getUri() {
        return ROOT+path;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","2");
        return jsonObject.toString();
    }

}
