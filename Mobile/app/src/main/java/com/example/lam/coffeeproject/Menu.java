package com.example.lam.coffeeproject;

import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.lam.coffeeproject.API.Requests.GetMenuRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.Model.CategoryModel;
import com.example.lam.coffeeproject.Model.MenuModel;
import com.example.lam.coffeeproject.Model.ProductModel;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    private Map<Integer,Integer> order = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GetMenuRequest getMenuRequest = getIntent().getParcelableExtra(TakeCoffeeService.EXTRA_REQUEST);
        try {
            MenuModel menuModel = getMenuRequest.getMenu();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            for (CategoryModel categoryModel : menuModel.getMenu().keySet()) {
                List<ProductModel> productModels = menuModel.getMenu().get(categoryModel);
                for (ProductModel productModel : productModels) {
                    ProductFragment fragment = new ProductFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("productJson", productModel.toJson().toString());
                    fragment.setArguments(bundle);
                    ft.add(R.id.productHolder, fragment, "product_" + productModel.getID());
                }
            }
            ft.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Location getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    void updateQuantity(int productId,int quantity){
        order.put(productId,quantity);
    }

    public void handleOrderButtonClick(View view) {
    }
}
