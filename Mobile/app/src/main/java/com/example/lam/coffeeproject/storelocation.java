package com.example.lam.coffeeproject;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class storelocation extends AppCompatActivity {
    Integer[] imageIDs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);

        ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);


        // Note that Gallery view is deprecated in Android 4.1---
        final Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
//                Toast.makeText(getBaseContext(),"pic" + (position + 1) + " selected",
//                        Toast.LENGTH_SHORT).show();
                // display the images selected
                ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
                try{
                    int img = imageIDs[position];
                    imageSwitcher.setImageResource(img);
                }catch (NullPointerException ex){
                    Toast.makeText(storelocation.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgView = new ImageView(storelocation.this);
                imgView.setBackgroundColor(Color.WHITE);
                imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imgView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT));
                return imgView;
            }
        });
        imageSwitcher.setImageResource(imageIDs[0]);


        imageSwitcher.setOnTouchListener(new OnSwipeTouchListener(this){
            public void onSwipeTop() {
                Toast.makeText(storelocation.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                int nextIndex = gallery.getSelectedItemPosition()-1;
                if(nextIndex < imageIDs.length && nextIndex >= 0){
                    gallery.setSelection(nextIndex,true);
                    ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
                    imageSwitcher.setImageResource(imageIDs[nextIndex]);
                }
            }
            public void onSwipeLeft() {
                int nextIndex = gallery.getSelectedItemPosition()+1;
                if(nextIndex < imageIDs.length && nextIndex >= 0){
                    gallery.setSelection(nextIndex,true);
                    ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
                    imageSwitcher.setImageResource(imageIDs[nextIndex]);
                }

            }
            public void onSwipeBottom() {
                Toast.makeText(storelocation.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return imageIDs.length;
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(500, 500));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }


    }

}
