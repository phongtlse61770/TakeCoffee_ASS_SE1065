package com.example.lam.coffeeproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ProductFragment fragment1 = new ProductFragment();
        ft.add(R.id.productHolder,fragment1 ,"11");

        ProductFragment fragment2 = new ProductFragment();
        ft.add(R.id.productHolder,fragment2 ,"11");

        ProductFragment fragment3 = new ProductFragment();
        ft.add(R.id.productHolder,fragment3 ,"11");


//        Fragment fragment2 = new Fragment();
//        ft.add(R.id.llContainer, fragment2, "fragment_two");

        ft.commit();
    }
}
