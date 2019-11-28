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
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.model.Trending;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrendingRestaurantListAdpter extends RecyclerView.Adapter<TrendingRestaurantListAdpter.ViewHolder> {
    private ArrayList<Trending> trendings;
    private Activity context;

    public TrendingRestaurantListAdpter(Activity context, ArrayList<Trending> notificationModelArrayList) {
        this.context = context;
        this.trendings = notificationModelArrayList;

    }

    // Add a list of items -- change to type used
    public void addAll(List<Trending> list) {
        trendings.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        trendings.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trending_restaurant_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtFoodCatName.setText(TextUtils.isEmpty(trendings.get(position).getFoodCategoryName()) ? " " : trendings.get(position).getFoodCategoryName());
        holder.txtFoodItem.setText(TextUtils.isEmpty(trendings.get(position).getItemName()) ? " " : trendings.get(position).getItemName());
        holder.txtPrise.setText(TextUtils.isEmpty(trendings.get(position).getItemPrice()) ? " " : "$"+trendings.get(position).getItemPrice()+".00");
        if (trendings.get(position).getRestaurantRatingAVG() != null) {
            holder.userRating.setRating(Float.parseFloat(trendings.get(position).getRestaurantRatingAVG().toString()));
        }
        if (trendings.get(position).getItemImage() != null) {
            Picasso.with(context)
                    .load(trendings.get(position).getItemImage())
                    .into(holder.imgRestaurant);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RestDetailsActivity.class)
                        .putExtra("restaurantID",trendings.get(position).getRestaurantID())
                        .putExtra("vegType","off"));
                context.overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendings.size();
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
        @BindView(R.id.txtFoodCatName)
        CustomTextView txtFoodCatName;
        @BindView(R.id.txtFoodItem)
        CustomTextView txtFoodItem;
        @BindView(R.id.linearEventDetail)
        RelativeLayout linearEventDetail;
        @BindView(R.id.userRating)
        RatingBar userRating;
        @BindView(R.id.txtPrise)
        CustomTextView txtPrise;


        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
