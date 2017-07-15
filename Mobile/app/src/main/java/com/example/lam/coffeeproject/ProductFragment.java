package com.example.lam.coffeeproject;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lam.coffeeproject.Model.MenuModel;
import com.example.lam.coffeeproject.Model.ProductModel;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    private ProductModel productModel;

    public ProductFragment() {
        // Required empty public constructor

        Bundle args = getArguments();
        String json = args.getString("productJson");
        try {
            this.productModel = ProductModel.FromJson(json);
        } catch (JSONException e) {
            Log.e(ProductFragment.class.getSimpleName(),"JSONException");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

}
