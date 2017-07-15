package com.example.lam.coffeeproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.lam.coffeeproject.API.Requests.GetMenuRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.Model.MenuModel;
import org.json.JSONException;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GetMenuRequest getMenuRequest = getIntent().getParcelableExtra(TakeCoffeeService.EXTRA_REQUEST);
        try {
            MenuModel menuModel = getMenuRequest.getMenu();


            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ProductFragment fragment1 = new ProductFragment();
            ft.add(R.id.productHolder, fragment1, "11");


            ft.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
