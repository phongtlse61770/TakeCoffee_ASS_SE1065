package com.example.lam.coffeeproject;

import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.lam.coffeeproject.API.Requests.GetMenuRequest;
import com.example.lam.coffeeproject.API.Requests.GetShippingFeeRequest;
import com.example.lam.coffeeproject.API.TakeCoffeeService;
import com.example.lam.coffeeproject.API.TakeCoffeeServiceHelper;
import com.example.lam.coffeeproject.Model.CategoryModel;
import com.example.lam.coffeeproject.Model.MenuModel;
import com.example.lam.coffeeproject.Model.ProductModel;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    private Map<Integer, Integer> order = new HashMap<>();
    private MenuModel menuModel;
    private Double shipFee;
    LocationManager lm;

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GetMenuRequest getMenuRequest = getIntent().getParcelableExtra(TakeCoffeeService.EXTRA_REQUEST);
        try {
            menuModel = getMenuRequest.getMenu();
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
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateLocation(location);
    }

    ResultReceiver getShippingFeeReceiver = new ResultReceiver(new Handler()) {
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            GetShippingFeeRequest getShippingFeeRequest = resultData.getParcelable(TakeCoffeeService.EXTRA_REQUEST);
            try {
                shipFee = getShippingFeeRequest.getFee();
                updateShippingFee(shipFee);
            } catch (Exception e) {
                Log.e(UserAPI.class.getSimpleName(), e.getMessage());
            }
        }
    };

    private void updateLocation(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        TakeCoffeeServiceHelper.getShippingFee(this, getShippingFeeReceiver, longitude, latitude);
    }

    private void updateShippingFee(double shipFee) {
        TextView textView = (TextView) findViewById(R.id.shipFeeTextView);
        textView.setText("Shipping fee: " + shipFee);
        updateTotalCost();
    }

    private void updateTotalCost() {
        double total = 0;
        for (Map.Entry<Integer, Integer> orderDetail : order.entrySet()) {
            int productId = orderDetail.getKey();
            int productQuantity = orderDetail.getValue();
            double productPrice = getUnitPrice(productId);
            total += productPrice * productQuantity;
        }

        if(shipFee != null){
            total += shipFee;
        }

        TextView textView = (TextView) findViewById(R.id.totalCostTextView);
        textView.setText("Your total: " + total);
    }

    private double getUnitPrice(int productId) {
        Double price = null;
        for (Map.Entry<CategoryModel, List<ProductModel>> entry : menuModel.getMenu().entrySet()) {
            for (ProductModel productModel : entry.getValue()) {
                if (productId == productModel.getID()) {
                    price = productModel.getPrice();
                }
            }
        }
        return price;
    }

    void updateQuantity(int productId, int quantity) {
        order.put(productId, quantity);
        updateTotalCost();
    }

    public void handleOrderButtonClick(View view) {
    }
}
