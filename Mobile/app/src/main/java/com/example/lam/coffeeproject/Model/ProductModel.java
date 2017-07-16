package com.example.lam.coffeeproject.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Phong on 7/15/2017.
 */
public class ProductModel {
    private int ID;
    private String name;
    private double price;
    private CategoryModel category;
    private boolean isAvailable;


    public ProductModel(int ID, String name, double price, CategoryModel category, boolean isAvailable) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public static ProductModel FromJson(JSONObject jsonObject) throws JSONException {
        int ID = jsonObject.getInt("ID");
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        JSONObject categoryJson = jsonObject.getJSONObject("category");
        CategoryModel category = CategoryModel.FromJson(categoryJson);
        boolean isAvailable = jsonObject.getBoolean("isAvailable");

        return new ProductModel(ID,name,price,category,isAvailable);
    }

    public static ProductModel FromJson(String json) throws JSONException {
        return FromJson(new JSONObject(json));
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID",ID);
        jsonObject.put("name",name);
        jsonObject.put("price",price);
        jsonObject.put("category",category.toJson());
        jsonObject.put("isAvailable",isAvailable);
        return jsonObject;
    }
}
