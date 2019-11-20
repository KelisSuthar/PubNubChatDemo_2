package com.addedfooddelivery_user.home.adpter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
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
import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantData;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.model.Popular;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PopularRestaurantListAdpter extends RecyclerView.Adapter<PopularRestaurantListAdpter.ViewHolder> {
    private ArrayList<Popular> populars;
    private Activity context;

    public PopularRestaurantListAdpter(Activity context, ArrayList<Popular> notificationModelArrayList) {
        this.context = context;
        this.populars = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<Popular> list) {
        populars.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        populars.clear();
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
        holder.txtRestauranrName.setText(TextUtils.isEmpty(populars.get(position).getRestaurantName()) ? " " : populars.get(position).getRestaurantName());
        holder.txtRestAdd.setText(TextUtils.isEmpty(populars.get(position).getRestaurantAddress()) ? " " : populars.get(position).getRestaurantAddress());
       // holder.txtRestaurantTime.setText(TextUtils.isEmpty(populars.get(position).getItemPrice()) ? " " : populars.get(position).getItemPrice());
        if (populars.get(position).getRestaurantRatingAVG() != null) {
            holder.userRating.setRating(populars.get(position).getRestaurantRatingAVG());
        }
        if (populars.get(position).getRestaurantImage() != null) {
            Picasso.with(context)
                    .load(populars.get(position).getRestaurantImage())
                    .into(holder.imgRestaurant);
        }
    }

    @Override
    public int getItemCount() {
        return populars.size();
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
        @BindView(R.id.txtRestaurantName)
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
