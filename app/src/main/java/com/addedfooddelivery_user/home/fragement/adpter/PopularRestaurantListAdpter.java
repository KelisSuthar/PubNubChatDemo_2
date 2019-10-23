package com.addedfooddelivery_user.home.fragement.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.RestDetailsActivity;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PopularRestaurantListAdpter extends RecyclerView.Adapter<PopularRestaurantListAdpter.ViewHolder> {
    private ArrayList<String> listData;
    private Activity context;

    public PopularRestaurantListAdpter(Activity context, ArrayList<String> notificationModelArrayList) {
        this.context = context;
        this.listData = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<String> list) {
        listData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        listData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_restaurant_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearEventImages)
        LinearLayout linearEventImages;
        @BindView(R.id.imgRestaurant)
        RoundedImageView imgRestaurant;
        @BindView(R.id.linearEvent)
        LinearLayout linearEvent;
        @BindView(R.id.txtRestauranrName)
        CustomTextView txtRestauranrName;
        @BindView(R.id.txtRestAdd)
        CustomTextView txtRestAdd;
        @BindView(R.id.linearEventDetail)
        RelativeLayout linearEventDetail;
        @BindView(R.id.userRating)
        RatingBar userRating;
        @BindView(R.id.txtRestaurantTime)
        CustomTextView txtRestaurantTime;

        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, RestDetailsActivity.class));
                    context.overridePendingTransition(R.anim.rightto, R.anim.left);
                }
            });
        }



    }
}
