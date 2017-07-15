package com.example.lam.coffeeproject.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/15/2017.
 */
public class CategoryModel {
    private int ID;
    private String name;

    public CategoryModel(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryModel FromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("ID");
        String name = jsonObject.getString("name");
        return new CategoryModel(id,name);
    }
}
