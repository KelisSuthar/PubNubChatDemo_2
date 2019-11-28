package com.addedfooddelivery_user.restaurantList.adpter;

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
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryData;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllResCategoryListAdpter extends RecyclerView.Adapter<AllResCategoryListAdpter.ViewHolder> {
    private ArrayList<AllRestCategoryData> allRestCategoryData;
    private Activity context;

    public AllResCategoryListAdpter(Activity context, ArrayList<AllRestCategoryData> notificationModelArrayList) {
        this.context = context;
        this.allRestCategoryData = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<AllRestCategoryData> list) {
        allRestCategoryData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        allRestCategoryData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_all_restaurant_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtRestauranrName.setText(TextUtils.isEmpty(allRestCategoryData.get(position).getRestaurantName()) ? " " : allRestCategoryData.get(position).getRestaurantName());
        holder.txtRestAdd.setText(TextUtils.isEmpty(allRestCategoryData.get(position).getRestaurantAddress()) ? " " : allRestCategoryData.get(position).getRestaurantAddress());
       // holder.txtRestaurantTime.setText(TextUtils.isEmpty(allRestCategoryData.get(position).getItemPrice()) ? " " : allRestCategoryData.get(position).getItemPrice());
        if (allRestCategoryData.get(position).getRestaurantRatingAVG() != 0) {
            holder.userRating.setRating(allRestCategoryData.get(position).getRestaurantRatingAVG());
        }else
            holder.userRating.setRating((float) 0.0);

        if (allRestCategoryData.get(position).getRestaurantImage() != null) {
            Picasso.with(context)
                    .load(allRestCategoryData.get(position).getRestaurantImage())
                    .into(holder.imgAllRestaurant);
        }
        if (allRestCategoryData.get(position).getItemImage() != null) {
            Picasso.with(context)
                    .load(allRestCategoryData.get(position).getItemImage())
                    .into(holder.imgAllRestaurant);
        }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RestDetailsActivity.class)
                        .putExtra("restaurantID", allRestCategoryData.get(position).getRestaurantID())
                        .putExtra("vegType","off"));
                context.overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allRestCategoryData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearEventImages)
        LinearLayout linearEventImages;
        @BindView(R.id.imgAllRestaurant)
        RoundedImageView imgAllRestaurant;
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

        }



    }
}
