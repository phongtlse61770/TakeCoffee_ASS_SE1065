package com.example.lam.coffeeproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Phong on 7/16/2017.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;
    private Exception ex;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            this.ex = e;
            Log.e("Error", e.getMessage());
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if(this.ex == null){
            bmImage.setImageBitmap(result);
        }
    }
}