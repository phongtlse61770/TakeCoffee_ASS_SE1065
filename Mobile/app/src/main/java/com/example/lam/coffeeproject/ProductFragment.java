package com.example.lam.coffeeproject;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lam.coffeeproject.Model.ProductModel;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    private ProductModel productModel;
    private int quantity;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parseJsonArgs();
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        SetupView();
    }

    private void handleUpButtonClick(View view) {
        quantity++;
        updateQuantityText();
    }

    private void handleDownButtonClick(View view) {
        if(quantity > 0){
            quantity--;
        }
        updateQuantityText();
    }

    private void updateQuantityText() {
        updateOrder();
        TextView productQuantityTextView = (TextView) getView().findViewById(R.id.productQuantityTextView);
        String quantity = this.quantity + "";
        productQuantityTextView.setText(quantity);
    }

    private void updateOrder(){
        Menu menuActivity = (Menu) this.getActivity();
        menuActivity.updateQuantity(productModel.getID(),quantity);
    }

    private void SetupView() {
        TextView productNameTextView = (TextView) getView().findViewById(R.id.productNameTextview);
        TextView productCategoryTextView = (TextView) getView().findViewById(R.id.productCategoryTextView);
        TextView productPriceTextView = (TextView) getView().findViewById(R.id.priceTextView);
        ImageView imageView = (ImageView) getView().findViewById(R.id.productImageView);

        productNameTextView.setText(productModel.getName());
        productCategoryTextView.setText(productModel.getCategory().getName());
        String price = productModel.getPrice() + "";
        productPriceTextView.setText(price);
        new DownloadImageTask(imageView).execute("https://unsplash.it/300/?random");

        Button upButton = (Button) getView().findViewById(R.id.upButton);
        Button downButton = (Button) getView().findViewById(R.id.downButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUpButtonClick(v);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDownButtonClick(v);
            }
        });
    }

    private void parseJsonArgs() {
        Bundle args = getArguments();
        String json = args.getString("productJson");
        try {
            this.productModel = ProductModel.FromJson(json);
        } catch (JSONException e) {
            Log.e(ProductFragment.class.getSimpleName(), "JSONException", e);
        }
    }

}
