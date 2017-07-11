package com.example.lam.coffeeproject.API.Requests;

import org.json.JSONException;

/**
 * Created by Phong on 7/10/2017.
 */
public class CheckLoginRequest extends BaseRequest{

    public CheckLoginRequest(String username, String password) {
        super(username, password);
    }

    @Override
    String getUri() {
        return null;
    }

    @Override
    String getRequestJsonBody() throws JSONException {
        return null;
    }
}
