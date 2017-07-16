package com.example.lam.coffeeproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import com.example.lam.coffeeproject.API.Requests.GetMenuRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.Model.CategoryModel;
import com.example.lam.coffeeproject.Model.MenuModel;
import com.example.lam.coffeeproject.Model.ProductModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GetMenuRequest getMenuRequest = getIntent().getParcelableExtra(TakeCoffeeService.EXTRA_REQUEST);
        try {
            MenuModel menuModel = getMenuRequest.getMenu();

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            for (CategoryModel categoryModel:
                 menuModel.getMenu().keySet()) {
                List<ProductModel> productModels = menuModel.getMenu().get(categoryModel);
                for (ProductModel productModel :
                        productModels) {
                    ProductFragment fragment = new ProductFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("productJson",productModel.toJson().toString());
                    fragment.setArguments(bundle);

                    ft.add(R.id.productHolder, fragment, "11");
                }
            }
//            ProductFragment fragment1 = new ProductFragment();
//            ft.add(R.id.productHolder, fragment1, "11");

            ft.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
