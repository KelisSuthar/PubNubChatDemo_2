package com.addedfooddelivery_user.RestaurantDetails.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class viewPagerAdapter extends PagerAdapter {
 
    private ArrayList<RestaurantImage> images;
    private LayoutInflater inflater;
    private Context context;
 
    public viewPagerAdapter(Context context, ArrayList<RestaurantImage> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }
 
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
 
    @Override
    public int getCount() {
        return images.size();
    }
 
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        Picasso.with(context).load(images.get(position).getRestauantImage()).into(myImage);

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}