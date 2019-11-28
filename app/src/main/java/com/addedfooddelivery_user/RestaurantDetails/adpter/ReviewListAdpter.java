package com.addedfooddelivery_user.RestaurantDetails.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantReview;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewListAdpter extends RecyclerView.Adapter<ReviewListAdpter.ViewHolder> {
    private ArrayList<RestaurantReview> reviewListData;
    private Context context;

    public ReviewListAdpter(Context context, ArrayList<RestaurantReview> notificationModelArrayList) {
        this.context = context;
        this.reviewListData = notificationModelArrayList;
    }

    // Add a list of items -- change to type used
    public void addAll(List<RestaurantReview> list) {
        reviewListData.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        reviewListData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review_listing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtReviewName.setText(TextUtils.isEmpty(reviewListData.get(position).getUserName().toString()) ? "" : reviewListData.get(position).getUserName().toString());
        holder.txtReviewDesc.setText(TextUtils.isEmpty(reviewListData.get(position).getComment().toString()) ? "" : reviewListData.get(position).getComment().toString());
        if (reviewListData.get(position).getRatings() != null) {
            holder.ratingBarReview.setRating(Float.parseFloat(reviewListData.get(position).getRatings()));
        }
        if (reviewListData.get(position).getCustomerAvatar() != null && !reviewListData.get(position).getCustomerAvatar().equalsIgnoreCase("")) {
            Picasso.with(context)
                    .load(reviewListData.get(position).getCustomerAvatar())
                    .into(holder.imgReviewer);
        }
    }

    @Override
    public int getItemCount() {
        return reviewListData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgReview)
        ImageView imgReviewer;
        @BindView(R.id.txtReviewName)
        CustomTextView txtReviewName;
        @BindView(R.id.review_desc)
        CustomTextView txtReviewDesc;
        @BindView(R.id.userReviewRat)
        RatingBar ratingBarReview;


        ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }
}
