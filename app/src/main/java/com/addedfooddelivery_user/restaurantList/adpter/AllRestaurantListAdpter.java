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
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantData;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllRestaurantListAdpter extends RecyclerView.Adapter<AllRestaurantListAdpter.ViewHolder> {
    private ArrayList<AllRestaurantData> allRestaurantData;
    private Activity context;

    public AllRestaurantListAdpter(Activity context, ArrayList<AllRestaurantData> notificationModelArrayList) {
        this.context = context;
        this.allRestaurantData = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<AllRestaurantData> list) {
        allRestaurantData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        allRestaurantData.clear();
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
        holder.txtRestauranrName.setText(TextUtils.isEmpty(allRestaurantData.get(position).getRestaurantName()) ? " " : allRestaurantData.get(position).getRestaurantName());
        holder.txtRestAdd.setText(TextUtils.isEmpty(allRestaurantData.get(position).getRestaurantAddress()) ? " " : allRestaurantData.get(position).getRestaurantAddress());
       // holder.txtRestaurantTime.setText(TextUtils.isEmpty(allRestaurantData.get(position).getItemPrice()) ? " " : allRestaurantData.get(position).getItemPrice());
        if (allRestaurantData.get(position).getRestaurantRatingAVG() != 0) {
            holder.userRating.setRating(allRestaurantData.get(position).getRestaurantRatingAVG());
        }else
            holder.userRating.setRating((float) 0.0);

        if (allRestaurantData.get(position).getRestaurantImage() != null) {
            Picasso.with(context)
                    .load(allRestaurantData.get(position).getRestaurantImage())
                    .into(holder.imgAllRestaurant);
        }
        if (allRestaurantData.get(position).getItemImage() != null) {
            Picasso.with(context)
                    .load(allRestaurantData.get(position).getItemImage())
                    .into(holder.imgAllRestaurant);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RestDetailsActivity.class)
                        .putExtra("restaurantID",allRestaurantData.get(position).getRestaurantID())
                        .putExtra("vegType","off"));
                context.overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allRestaurantData.size();
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
