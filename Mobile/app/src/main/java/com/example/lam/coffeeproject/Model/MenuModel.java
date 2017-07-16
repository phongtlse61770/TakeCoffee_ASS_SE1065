package com.example.lam.coffeeproject.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Phong on 7/15/2017.
 */
public class MenuModel{
    private Map<CategoryModel,List<ProductModel>> menu;

    public MenuModel(Map<CategoryModel, List<ProductModel>> menu) {
        this.menu = menu;
    }

    public Map<CategoryModel, List<ProductModel>> getMenu() {
        return menu;
    }

    public void setMenu(Map<CategoryModel, List<ProductModel>> menu) {
        this.menu = menu;
    }

    public static MenuModel FromJson(JSONArray jsonArray) throws JSONException {
        Map<CategoryModel, List<ProductModel>> menu = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int categoryId = jsonObject.getInt("ID");
            String categoryName = jsonObject.getString("name");
            JSONArray categoryProducts = jsonObject.getJSONArray("products");

            CategoryModel categoryModel = new CategoryModel(categoryId,categoryName);
            List<ProductModel> productModels = new LinkedList<>();

            for (int j = 0; j < categoryProducts.length(); j++) {
                JSONObject productJson = categoryProducts.getJSONObject(j);
                productModels.add(ProductModel.FromJson(productJson));
            }
            menu.put(categoryModel,productModels);
        }
        return new MenuModel(menu);
    }
}
